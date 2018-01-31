function viewItems() {

    var all = false;
    //console.log(all);
    if(document.getElementById('checkDone').checked) {
        all = true;
    }

    //console.log(all);

    $.ajax({
        url: "main",
        method: "get",
        datatype: 'json',
        data: {
            'done': all
        },
        complete: function (data) {
            var result = JSON.parse(data.responseText);
            if(result.items != ''){
                var next =  JSON.parse(result.items);
                var optional = "";
                for (var i = 0; i != next.length; ++i) {
                    optional += "<tr>";
                    optional += "<td>"+next[i].id+"</td>";
                    optional += "<td>"+next[i].description+"</td>";
                    optional += "<td>"+next[i].created+"</td>";
                    var flag = (next[i].done === false);
                    //console.log(flag);
                    if (flag) {
                        optional += "<td>"+'<input type="checkbox" id="' + next[i].id + '" value="'+next[i].done+'"' +
                            ' onclick="updateItem(this.id, this.value)""></input>'+"</td>";
                    } else {
                        optional += "<td>"+'<input type="checkbox" checked id="' + next[i].id + '" value="'+next[i].done+'"' +
                            ' onclick="updateItem(this.id, this.value)""></input>'+"</td>";                    }
                    optional += "<td>"+
                        '<button class="delete" id="' + next[i].id + '" onclick="deleteItem(this.id)">delete</button>'+"</td>";
                    optional += "</tr>";
                }
                var table = document.getElementById("tbody");
                table.innerHTML = optional;
            }
        }
    });
}

function deleteItem(id) {
    console.log(id);
    $.ajax({
        url: "del",
        method: "post",
        data: {
            'id' : id
        },
        complete: function () {
            clear();
            viewItems();
        }
    });
}

function updateItem(id, value) {
    //console.log("checkbox id", id);
    //console.log("boolean value", value);
    $.ajax({
       url: "upd",
       method: "post",
       data: {
           'id' : id,
           'value' : value
       },
       complete: function () {
           clear();
           viewItems();
       }
    });
}


function clear() {
    $('input').val('');
};

$(document).ready(function () {

    viewItems();

    $("#create").click(function () {
        var description = $("#description");

        var done = false;

        if($("#done").prop('checked')){
            done = true;
        }


        if(description.val() != ''){
            $.ajax({
                url: "main",
                method: "post",
                data: {
                    'description': description.val(),
                    'done': done},
                complete: function (data) {
                    var result = JSON.parse(data.responseText);
                    if (!Boolean(result.Success)) {
                        alert("Error. Try again");
                    }
                    clear();
                    viewItems();
                }
            });
        }


    });

    $("#checkDone").click(function () {
        viewItems();
    });

})