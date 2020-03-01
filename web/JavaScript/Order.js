window.onload = function () {

    var leftBox1=document.getElementById("leftBox1");
    var leftBox2=document.getElementById("leftBox2");
    var leftBox3=document.getElementById("leftBox3");
    var leftBox4=document.getElementById("leftBox4");
    var leftH=document.getElementById("leftH");
    var li1=document.getElementById("li1");
    var li2=document.getElementById("li2");
    var li3=document.getElementById("li3");
    var li4=document.getElementById("li4");
    li1.onclick=function () {
        li1.className="active";
        leftBox1.className="active";
        leftBox2.className="positive";
        leftBox3.className="positive";
        leftBox4.className="positive";
        li2.className="";
        li3.className="";
        li4.className="";
    }
    li2.onclick=function () {
        li2.className="active";
        leftBox2.className="active";
        leftBox1.className="positive";
        leftBox3.className="positive";
        leftBox4.className="positive";
        li1.className="";
        li3.className="";
        li4.className="";
    }
    li3.onclick=function () {
        li3.className="active";
        leftBox3.className="active";
        leftBox2.className="positive";
        leftBox1.className="positive";
        leftBox4.className="positive";
        li2.className="";
        li1.className="";
        li4.className="";
    }
    li4.onclick=function () {
        li4.className="active";
        leftBox4.className="active";
        leftBox2.className="positive";
        leftBox3.className="positive";
        leftBox1.className="positive";
        li2.className="";
        li3.className="";
        li1.className="";
    }

}