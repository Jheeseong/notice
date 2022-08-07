'use strict';

let replyIndex = {
    init: function () {
        $("#reply-btn-save").on("click", () => {
            this.replySave();
        });
    },

    replySave: function () {
        let data = {
            content: $("#reply-content").val(),
        }
        let boardDtoId = $("#boardDtoId").val();
        console.log(data);
        console.log(boardDtoId);
        $.ajax({
            type: "POST",
            url: `/board/post/comment/${boardDtoId}`,
            dataType: "text"
        }).done(function (res) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/post/${boardDtoId}`;
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

}
replyIndex.init();