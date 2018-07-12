function login() {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_login";
    document.querySelector('.cont_form_login').style.display = "block";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";
    setTimeout(function () {
        document.querySelector('.cont_form_login').style.opacity = "1";
    }, 400);
    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
    }, 200);
}

function sign_up(at) {
    document.querySelector('.cont_forms').className = "cont_forms cont_forms_active_sign_up";
    document.querySelector('.cont_form_sign_up').style.display = "block";
    document.querySelector('.cont_form_login').style.opacity = "0";
    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.opacity = "1";
    }, 100);
    setTimeout(function () {
        document.querySelector('.cont_form_login').style.display = "none";
    }, 400);
}


function login_sign_up() {

    document.querySelector('.cont_forms').className = "cont_forms";
    document.querySelector('.cont_form_sign_up').style.opacity = "0";
    document.querySelector('.cont_form_login').style.opacity = "0";

    setTimeout(function () {
        document.querySelector('.cont_form_sign_up').style.display = "none";
        document.querySelector('.cont_form_login').style.display = "none";
    }, 500);

}

$(document).ready(function(){
    var headers = {};
    headers['X-CSRF-TOKEN'] = $("meta[name='_csrf']").attr("content");
    $('#btn-login').on('click', function () {
        $.ajax({
            "url": "login",
            "type": "POST",
            "headers": headers,
            "data": {
                username: $('#username').val(),
                password: $('#password').val()
            },
            "success": function (res) {
                console.log(res);
            }
        });
    });
});