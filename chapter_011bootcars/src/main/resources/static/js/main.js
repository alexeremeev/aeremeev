var login_result = false;
var CONTEXT_PATH = document.getElementsByTagName('meta')[1].content;

$(document).ready(function () {

    validateSession();
    viewOrders();
    updateTable();

    $("#auth-btn").click(function () {
        var data = 'username=' + $('#username').val() + '&password=' + $('#password').val();
        console.log('data', data);
            $.ajax({
                url: "login",
                type: "post",
                timeout: 1000,
                data: data})
                .done(function (data, textStatus) {
                    console.log('status', textStatus);
                    login_result = true;
                    viewOrders();
                    updateTable();
            }).fail(function (jqXHR, textStatus, errorThrown) {
                console.log('data', errorThrown);
                console.log('status', textStatus);
                console.log('xhr', jqXHR);
                alert('Fail!');
            });

    });

    $("#add-user-btn").click(function () {
        alert('Sorry. Registrations are closed for short period...');
        //will be added
    });

    $("#move-order-page").click(function () {
        if(login_result){
            $.ajax({
                url: "edit",
                method: "post",
                data: { "order" : -1}
            });
            location.href = CONTEXT_PATH.concat("/create");
        }
    });

    $('.btn-close-modal').click(function() {
        $('.modalWindow').fadeOut('slow');
        $('.carousel-inner').html('');
    });

    $("#flt-ord-btn").click(function(){
        filterTable();
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
        url: "view",
        method: "get",
        complete: function (data) {
            var result = JSON.parse(data.responseText);
            var userId = result.currentUser;
            var orders = JSON.parse(result.orders);
            if(result.orders != ''){
                var optional = "";
                for (var i = 0; i != orders.length; ++i) {
                    var userOrder = orders[i].userName;
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
                $("#table-body").html(optional);
            }
        }
    });
}

function filterTable(){

    var modelInput = $("#model").val();
    var model = "%".concat(modelInput);
    var isEmpty = -1;
    if($("#photo").prop('checked')) {
        isEmpty = 0;
    }
    var orderDate = 0;
    var date = new Date();
    if ($("#orderDate").prop('checked')) {
        orderDate = date.getTime() - 1000 * 60 *60 * 24;
    }

    $.ajax({
        url: "filter",
        method: "get",
        data: {
            'model' : model,
            'isEmpty' : isEmpty,
            'orderDate' : orderDate
        },
        complete: function (data) {
            var result =  JSON.parse(data.responseText);
            var userId =  result.currentUser;
            var orders =  JSON.parse(result.orders);
            if(result.orders != "[]"){
                var optional = "";
                for (var i = 0; i != orders.length; ++i) {

                    var userOrder = orders[i].userName;
                    console.log(userOrder);
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
                $("#table-body").html(optional);
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
            location.href = CONTEXT_PATH.concat("/create");
        }
    });
}
