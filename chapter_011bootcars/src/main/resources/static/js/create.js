var order_id = -1;
var carId = -1;

$(document).ready(function () {

    fillParts("Body", "body");
    fillParts("Transmission", "transmission");
    fillParts("Engine", "engine");
    fillParts("Model", "model");
    fillParts("Gearbox", "gearbox");
    fillExistOrder();

    $("#add-order-btn").click(function () {

        var sold = false;

        if($("#sold").prop('checked')){
            sold = true;
        }

        var release = $("#release");
        var car = {
            'name' : $("#name").val(),
            'body' : { 'id': $("#body").val()},
            'gearbox' : {'id' : $("#gearbox").val() },
            'engine' : {'id' : $("#engine").val()},
            'transmission' : {'id':$("#transmission").val()},
            'model' : {'id': $("#model").val()}
        };
        var order = {
            'price' : $("#price").val(),
            'mileage' : $("#mileage").val(),
            'sold' : sold
        };

        $.ajax({
            url: "create",
            method: "post",
            datatype : 'JSON',
            data: {
                'order' : JSON.stringify(order),
                'car'   : JSON.stringify(car),
                'release' : release.val(),
                'carId' : carId
            },
            complete: function (data) {
                order_id  = JSON.parse(data.responseText);
                if (order_id != -1) {
                    alert("Объявление добавлено. Пожалуйста добавьте фотографии машины.");
                    $('#add-order-btn').prop('disabled',true);
                }
            }
        });
    });


    $("#file-btn-load").click(function() {

        var data = new FormData();

        $.each($('#file-image')[0].files, function(i, file) {
            data.append('file-'+i, file);
        });

        if(order_id != -1){
            $.ajax({
                url: "image",
                method: "post",
                processData : false,
                contentType : false,
                data: data,
                complete: function (data) {
                    var result  = JSON.parse(data.responseText);
                    if (result.success) {
                        alert("Фотографии добавлены.");
                    }
                }
            });
        }
    });

});

function fillParts(partName, tableName) {
    $.ajax({
        url: "fill",
        method: "get",
        data: {'type': partName},
        complete: function (data) {
            var next = JSON.parse(data.responseText);
            if(next != ''){
                var optional = "";
                for (var i = 0; i != next.length; ++i) {
                    optional += "<option value = "+next[i].id+">" + next[i].name + "</option>";
                }
                var dropdownMenu = document.getElementById(tableName);
                dropdownMenu.innerHTML = optional;
            }
        }
    });
}

function fillExistOrder() {
    $.ajax({
        url: "edit",
        method: "get",
        complete: function (data) {
            var array = JSON.parse(data.responseText);
            order_id = array.order;
            if (order_id != -1) {
                var orderProp = array.orderProperties[0];
                $("#model").val(orderProp.modelId);
                $("#body").val(orderProp.bodyId);
                $("#gearbox").val(orderProp.gearboxId);
                $("#engine").val(orderProp.engineId);
                $("#transmission").val(orderProp.transmissionId);
                $("#name").val(orderProp.carName);
                $("#price").val(orderProp.price);
                $("#mileage").val(orderProp.mileage);
                $("#sold").prop('checked',orderProp.sold)
                $("#release").val(orderProp.release);
                $("#titleOrder").html("Редактировать объявление");
                $("#add-order-btn").html("Обновить");
                carId = orderProp.carId;
            }
        }
    });
}

function goBack() {
    window.history.back();
}

