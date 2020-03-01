var xhr;
function checkUserName() {
    var username=document.getElementById('username').value;
    xhr=new XMLHttpRequest();
    xhr.onreadystatechange=process;
    xhr.open("GET","usernameIsExist?username="+username,true);
    xhr.send(null);
}
function process() {
    if (xhr.readyState==4){
        if (xhr.status==200){
            var responseInfo=xhr.responseText;
            var msg =document.getElementById('isExist');
            if (responseInfo=='username is null'){
                msg.classList.remove("isNotExist");
                msg.classList.add('isExist');
                msg.innerText='请填写用户名';
            }
            else if (responseInfo=='Exist'){
                msg.classList.remove("isNotExist");
                msg.classList.add('isExist');
                msg.innerText='用户名已存在';
            }else if (responseInfo=='Not Exist'){
                msg.classList.remove("isExist");
                msg.classList.add('isNotExist');
                msg.innerText='用户名可以使用';
            }
        }
    }
}
function accountExist() {
    var responseInfo=xhr.responseText;
    if (responseInfo=='Exist'){
        alert("用户名已经存在！请重新输入！");
        return false;
    }
    else if (document.getElementById("password").value!=document.getElementById("repeatedPassword").value){
        alert("两次输入的密码不一致！请重新输入！");
        return false;
    }
}
function checkPassword() {
    var password = document.getElementById("password").value;
    var repeatpassword=document.getElementById("repeatedPassword").value;
    var msg =document.getElementById('notEqual');
    if (password!=repeatpassword&&(repeatpassword!=null||repeatpassword!="")){
        msg.classList.add('isExist');
        msg.innerText='两次输入的密码不一样';
    }else {
        msg.innerText='';
    }
}
function fixPasswordWrong() {
    if (document.getElementById("fixPassword").value!=document.getElementById("repeatFixPassword").value){
        alert("两次输入的密码不一致！请重新输入！");
        return false;
    }
}