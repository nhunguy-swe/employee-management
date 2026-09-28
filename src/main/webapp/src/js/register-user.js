function validateRegister() {
    var firstName = document.getElementById("firstName");
    var lastName = document.getElementById("lastName");
    var email = document.getElementById("email");
    var userName = document.getElementById("userName");
    var password = document.getElementById("password");
    var confirmPassword = document.getElementById("confirmPassword");

    var message = "";
    var isValid = true;

    // Reset màu border
    [firstName, lastName, email, userName, password, confirmPassword].forEach(el => {
        setBorderColor(el);
    });

    // 1. Kiểm tra trống (dựa trên màu border đã set bởi setBorderColor)
    if (firstName.value === "" || lastName.value === "" || email.value === "" ||
        userName.value === "" || password.value === "" || confirmPassword.value === "") {
        message = "Please fill all mandatory fields";
        isValid = false;
    }
    // 2. Kiểm tra định dạng Email
    else if (!validateEmail(email.value)) {
        message = "Email is incorrect format";
        email.style.borderColor = "red";
        isValid = false;
    }
    // 3. Kiểm tra độ dài Username
    else if (userName.value.length < 5) {
        message = "Username must be at least 5 characters";
        userName.style.borderColor = "red";
        isValid = false;
    }
    // 4. Kiểm tra khớp mật khẩu
    else if (password.value !== confirmPassword.value) {
        message = "Confirm password does not match";
        confirmPassword.style.borderColor = "red";
        isValid = false;
    }

    // Hiển thị thông báo lỗi
    document.getElementById("error").innerHTML = message;

    // QUAN TRỌNG: Trả về isValid để form biết có được submit hay không
    return isValid;
}

function validateEmail(email) {
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function setBorderColor(element) {
    if (element.value === "") {
        element.style.borderColor = "red";
    } else {
        element.style.borderColor = "green";
    }
}