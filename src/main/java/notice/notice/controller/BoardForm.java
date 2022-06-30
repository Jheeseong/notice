package notice.notice.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class BoardForm {

    private Long id;

    @NotEmpty
    private String title;

    private String content;

}
