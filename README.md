# notice(게시판 프로젝트)
Spring 학습을 위한 1인 프로젝트 제작

## - 사용 스택
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

## Backend architecture
### Routes 소개

#### 게시판 홈페이지(board)

      @GetMapping("/")
      public String home(Model model, @LoginUser SessionUser user) {


          if (user == null) {
              return "home";
          }

          model.addAttribute("userName", user.getName());
          model.addAttribute("userImg", user.getPicture());

          return "loginHome";
      }
      
- LoginSession 에노테이션을 통해 로그인 여부 파악 후 서로 다른 페이지를 전송

#### 게시판 리스트(board/list)

      @GetMapping({"", "/list"})
      public String list(Model model, @RequestParam(value="page", defaultValue = "1") Integer pageNum) {
          List<BoardDto> boardList = boardService.getBoardList(pageNum);
          Integer[] pageList = boardService.getPageList(pageNum);

          model.addAttribute("boardList", boardList);
          model.addAttribute("pageList", pageList);

          return "board/list";
      }
    
    
- 게시판에서 작성된 리스트 확인
- 페이징을 통해 4개 초과 시 다음 페이지로 설계   


#### 카테고리 별 게시판 리스트(board/list/1 or 2 or 3)

      @GetMapping("/list/{category}")
      public String categoryList(@PathVariable("category") Long categoryId, @RequestParam(value = "page", defaultValue = "1") Integer pageNum, Model model) {
          List<BoardDto> boardDtoList = boardService.getBoardCategoryList(pageNum, categoryId);
          Integer[] pageList = boardService.getPageList(pageNum);

          model.addAttribute("boardCategoryList", boardDtoList);
          model.addAttribute("pageCategoryList", pageList);

          return "board/categoryList";
      }
      
- 카테고리 별 보드 리스트 확인
- 페이징을 통해 4개 초과 시 다음 페이지로 설계

#### 글 작성(board/post)

      @GetMapping("/post")
      public String write(@LoginUser SessionUser user, Model model) {

          List<CategoryDto> categoryDtoList = categoryService.AllCategory();

          BoardDto boardDto = new BoardDto();
          boardDto.setWriter(user.getName());
          model.addAttribute("boardDto", boardDto);
          model.addAttribute("categories", categoryDtoList);
          return "board/write";
      }
      
- 글 작성 페이지

      @PostMapping("/post")
        public String write(@Valid BoardDto boardDto, BindingResult bindingResult, Model model,
                        @LoginUser SessionUser sessionUser) {

            List<CategoryDto> categoryDtoList = categoryService.AllCategory();
            model.addAttribute("categories", categoryDtoList);

            if (bindingResult.hasErrors()) {
                log.info("errors={}", bindingResult);
                return "board/write";
            }

            boardService.savePost(boardDto, sessionUser.getEmail());
            return "redirect:/board/list";
        }

- 작성 내용을 POST 메서드로 DB 저장
- 저장 후 board/list로 리다이렉션

#### 게시물 상세 페이지(board/post/1...)

      @GetMapping("/post/{no}")
        public String detail(@PathVariable("no") Long no, Model model) {
            BoardDto boardDTO = boardService.getPost(no);

            model.addAttribute("boardDto", boardDTO);
            return "board/detail";
        }

- PathVariable 에노테이션을 통해 {no}를 받고 DB 불러옴

#### 게시물 수정 페이지(board/post/edit/1...)

      @GetMapping("/post/edit/{no}")
      public String edit(@PathVariable("no") Long no, Model model) {
          BoardDto boardDTO = boardService.getPost(no);

          model.addAttribute("boardDto", boardDTO);
          return "board/update";
      }
      
- 게시물을 수정하는 페이지
- @Build 에노테이션을 이용하여 setter 구현을 통해 생기는 불필요한 변경 가능성을 최소화

      @PutMapping("/post/edit/{no}")
      public String update(BoardDto boardDTO, @LoginUser SessionUser sessionUser) {
          boardService.savePost(boardDTO, sessionUser.getEmail());

          return "redirect:/board/list";
      }

- PUT 메서드를 이용해 게시물ㅇ 수정한 부분에 대해 적용
- board/list 로 리다이렉션

#### 게시물 삭제 페이지(board/post/1....)

      @DeleteMapping("/post/{no}")
      public String delete(@PathVariable("no") Long no) {
          boardService.deletePost(no);

          return "redirect:/board/list";
      }
      
- DeletePost 메서드를 사용하여 게시물을 삭제
- board/list 로 리 다이렉션

#### 게시물 검색 페이지(board/search)

      @GetMapping("/search")
      public String search(@RequestParam(value="keyword") String keyword, Model model) {
          List<BoardDto> boardDtoList = boardService.searchPosts(keyword);

          model.addAttribute("boardList", boardDtoList);

          return "board/list";
      }
      
- Keyword를 view로부터 전달 받은 후 Service로부터 받은 boardDtoList를 model의 attribute로 전달

#### ㄷㅅ글 작성 ㅔ이지

      public class CommentController {

          private final CommentService commentService;
         @PostMapping("/post/comment/{no}")
          public String createComment(@PathVariable(name = "no") Long boardId,
                                      @RequestBody CommentDto commentDto,
                                      @LoginUser SessionUser user) {
              commentService.createComment(boardId, commentDto, user.getEmail());

              return "redirect:/board/detail";
          }
      }
     
   
## - DB 설계

![image](https://user-images.githubusercontent.com/96407257/180646660-f90c4a2c-ec90-4dc2-8fc5-a31f5e5a4b78.png)

## - 구조

- Controller
  - BoardController
  - HomeController
  
- Domain
  - Board
  - Category
  - User
  
- DTO
  - BoardDto
  - CategoryDto
  - OAuthAttributes(Spring Security를 통한 구글 로그인)
  
- Repository
  - BoardRepository
  - CategoryRepository
  - UserRepository
  
- Service
  - BoardService
  - CategoryService
  - CustomOAuth2UserService(구글 로그인 서비스)
  
- View
  - home(홈 화면)
  - loginHome(로그인 홈 화면)
  - write(게시글 작성)
  - list(게시글 리스트)
  - update(게시글 수정)
  - detail(게시글 내용)
  - categoryList(게시글 카테고리별 분류)
