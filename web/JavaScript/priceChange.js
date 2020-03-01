$(".inputQuantity").keyup(function (){
    this.value=this.value.replace(/\D|^0/g,'');
    var xhr = new XMLHttpRequest();
    var itemQuantityId = event.srcElement.getAttribute("id");
    //这里是不是变量名有问题
    var quantity = document.getElementById(itemQuantityId).value;

    xhr.onreadystatechange = processResponse;
    xhr.open("GET", "updateCart?itemQuantityId="+itemQuantityId+"&quantity="+quantity, true);
    xhr.send(null);

    function processResponse() {
        if (xhr.readyState == 4) {
            if (xhr.status == 200) {
                var resp = xhr.responseText;
                if(1)
                {
                    var itemTotal = itemQuantityId.replace("Quantity","")+"Total";
                    var itemPrice = itemQuantityId.replace("Quantity","")+"Price";
                    var subTotal = document.getElementById("itemSubTotal").innerText.replace("$","").replace(",","");
                    var itemTempTotal = document.getElementById(itemTotal).innerText.replace("$","").replace(",","");

                    var itemTempPrice = document.getElementById(itemPrice).innerText.replace("$","").replace(",","");
                    document.getElementById(itemTotal).innerText ="$"+ itemTempPrice * quantity;
                    var newSubTotal = subTotal + itemTempPrice * quantity - itemTempTotal;
                    document.getElementById("itemSubTotal").innerText ="Sub Total: $"+resp;

                }

            }
        }
    }
})