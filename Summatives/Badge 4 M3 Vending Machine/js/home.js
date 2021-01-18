$(document).ready(function () {
    loadItems();
    addQuarter();
    addDollar();
    addNickel();
    addDime();
});

function loadItems() {
    resetMoney();
    var itemsRow = $('#itemsRow');
    var itemNumber = 1;
    $.ajax({
        type: 'GET',
        url: 'http://tsg-vending.herokuapp.com/items',
        success: function (itemsArray) {
            $.each(itemsArray, function (index, item) {

                var itemName = item.name;
                var itemPrice = item.price;
                var itemQuantity = item.quantity;

                var itemId = item.id;

                var row = '<div class="col-md-3 border border-primary my-2">';
                row += '<p>' + itemNumber + '</p>';
                row += '<p class="text-center">' + itemName + '</p>';
                row += '<p class="text-center">' + itemPrice + '</p>';
                row += '<p class="text-center">Quantity Left: ' + itemQuantity + '</p>';
                row += '</div>';

                row += '<div class="col-md-1">';
                row += '</div>';


                itemsRow.append(row);
                itemNumber++;
            })
        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}
function resetMoney() {
    var reset = 0.00;
    var floatReset = parseFloat(reset);
    $('#totalMoney').val(floatReset.toFixed(2));
}



function addQuarter() {
    $('#addQuarter').click(function () {

        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 0.25;
        moneyField.val(totalMoney.toFixed(2));

    });
}
function addDollar() {
    $('#addDollar').click(function () {
        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 1.00;
        moneyField.val(totalMoney.toFixed(2));

    });
}
function addNickel() {
    $('#addNickel').click(function () {
        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 0.05;
        moneyField.val(totalMoney.toFixed(2));

    });
}
function addDime() {
    $('#addDime').click(function () {
        var moneyField = $('#totalMoney');
        var totalMoney = $('#totalMoney').val();
        totalMoney = parseFloat(totalMoney) + 0.10;
        moneyField.val(totalMoney.toFixed(2));

    });
}