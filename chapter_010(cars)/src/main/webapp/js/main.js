var login_result;

$(document).ready(function () {

    validateSession();
    updateTable();

    $("#auth-btn").click(function () {
        var login = $("#login");
        var password = $("#password");

        if(login != '' && password != ''){
            $.ajax({
                url: "login",
                method: "post",
                data: {
                    'login': login.val(),
                    'password': password.val(),
                    'register' : false},
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    login_result = Boolean(result.success);
                    if (login_result) {
                         viewOrders();
                         updateTable();
                    }
                    else {
                        alert("Invalid login / password");
                    }
                }
            });
        }
    });

    $("#add-user-btn").click(function () {
        var login = $("#login");
        var password = $("#password");

        if(login != '' && password != ''){
            $.ajax({
                url: "login",
                method: "post",
                data: {
                    'login': login.val(),
                    'password': password.val(),
                    'register' : true},
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    login_result = Boolean(result.success);
                    if (login_result) {
                         viewOrders();
                         updateTable();
                    }
                    else {
                        alert("Error. Try again");
                    }
                }
            });
        }
    });

    $("#move-order-page").click(function () {
        if(login_result){
            $.ajax({
                url: "edit",
                method: "post",
                data: { "order" : -1}
            });
            location.href = "create.html";
        }
    });

    $('.btn-close-modal').click(function() {
        $('.modalWindow').fadeOut('slow');
        $('.carousel-inner').html('');
    });

});


function validateSession() {
    $.ajax({
        url: "login",
        method: "get",
        complete: function (data) {
            var result = JSON.parse(data.responseText);
            login_result = Boolean(result.success);
            viewOrders();
        }
    });
}

function viewOrders() {
    if (login_result) {
        $("#authorisation").hide();
        $("#info_success_auth").show();
        $("#move-order-page").show();
    }
}

function updateTable(){
    $.ajax({
        url: "create",
        method: "get",
        complete: function (data) {
            var result =  JSON.parse(data.responseText);
            var userId =  result.currentUser;
            var orders   =  JSON.parse(result.orders);
            if(result.orders != ''){
                var optional = "";
                for (var i = 0; i != orders.length; ++i) {
                    var userOrder = orders[i].userId;
                    var flag = (orders[i].sold === true);
                    optional += "<tr>";
                    if (flag) {
                        optional += "<td>Продано</td>";
                    } else {
                        optional += "<td>Продается</td>";
                    }
                    optional += "<td>"+orders[i].carName+"</td>";
                    optional += "<td>"+orders[i].price+"</td>";
                    optional += "<td>"+orders[i].mileage+"</td>";
                    optional += "<td>"+orders[i].date+"</td>";
                    optional += "<td><button type='button' class='btn btn-link pictures' value='"+orders[i].orderId+"' onclick= 'callGallery(" + orders[i].orderId + ")' >Галерея</button></td>";
                    if(userOrder == userId){
                        optional += "<td><button type='button' class='btn btn-link' onclick= 'editOrder(" + orders[i].orderId + ")' ><i class= 'material-icons' style='font-size:20px'>mode_edit</i></button></td>";
                    } else {
                        optional += "<td><button type='button' class='btn btn-link' disabled ><i class= 'material-icons' style='font-size:20px'>mode_edit</i></button></td>";
                    }
                    optional += "</tr>";
                }
                var table = document.getElementById("table-body");
                table.innerHTML = optional;
            }
        }
    });
}

function callGallery(orderId) {
    $.ajax({
        url: "image",
        method: "get",
        data: {'order': orderId},
        complete: function (data) {

            var images = JSON.parse(data.responseText);
            if (images != '') {
                var optional = "";
                for (var i = 0; i != images.length; ++i) {

                    optional += "<div class='item active'>";

                    optional += "<img src= '" + images[i] + "'   width='460' height='345'>";

                    optional += "</div>";
                }
                var table = document.getElementById("carousel-body");
                table.innerHTML = optional;
            }
        }
    });
    $('.modalWindow').fadeIn('slow');
}

function editOrder(orderId) {
    $.ajax({
        url: "edit",
        method: "post",
        datatype : 'json',
        data : {"order": orderId},
        complete: function () {
            location.href = "create.html";
        }
    });
}
