package notice.notice.validation.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateBoardForm {

    @NotBlank
    private String BoardWriter;

    @NotBlank
    private String BoardTitle;

    @NotNull
    private String content;
}
