let replyIndex = {
    init: function () {
        $("#reply-btn-save").on("click", () => {
            this.replySave();
        });
    },

    replySave: function () {
        let data = {
            contents: $("#comment-contents").val(),
        }
        let boardId = $("#boardId").val();
        console.log(data);
        console.log(boardId);
        $.ajax({
            type: "POST",
            url: `/board/post/${boardId}/comment`,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "text"
        }).done(function (res) {
            alert("댓글작성이 완료되었습니다.");
            location.href = `/board/post/${boardId}`;
        }).fail(function (err) {
            alert(JSON.stringify(err));
        });
    },

}
replyIndex.init();