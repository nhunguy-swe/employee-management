$(document).ready(function() {
    // Gọi form thêm nhân viên
    $("#addEmpLink").click(function(e) {
        e.preventDefault();
        $.get({
            url : contextPath + "/add-employee",
            success : function(response) {
                $("#main-content").html(response);
            }
        });
    });

    // Gọi danh sách nhân viên
    $("#listEmpsLink").click(function(e) {
        e.preventDefault();
        $.get({
            url : contextPath + "/list-employees",
            success : function(response) {
                $("#main-content").html(response);
            }
        });
    });
});