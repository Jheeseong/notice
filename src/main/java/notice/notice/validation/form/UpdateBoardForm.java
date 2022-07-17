package notice.notice.validation.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateBoardForm {

    @NotNull
    private Long id;

    @NotBlank
    private String BoardWriter;

    @NotBlank
    private String BoardTitle;

    @NotNull
    private String content;
}
