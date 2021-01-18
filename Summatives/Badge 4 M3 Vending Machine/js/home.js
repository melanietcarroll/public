$(document).ready(function () {
    loadItems();
    addQuarter();
    addDollar();
    addNickel();
    addDime();
    changeReturn();
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


function makeChange() {

    var change = $('#totalMoney').val();
    var floatMoney = parseFloat(change);
    var cents = floatMoney * 100;
//    var stringCents = cents.toString();
    var dollars = parseInt(cents / 100);
    cents = cents % 100;
    var quarters = parseInt(cents / 25);
    cents = cents % 25;
    var dimes = parseInt(cents / 10);
    cents = cents % 10;
    var nickels = parseInt(cents / 5);
    cents = cents % 5;
    var pennies = parseInt(cents / 1);
    var stringDollars = dollars.toString();
    var stringQuarters = quarters.toString();
    var stringDimes = dimes.toString();
    var stringNickels = nickels.toString();
    var stringPennies = pennies.toString();

   
    $('#totalChange').val("D:" + stringDollars + "Q:" + stringQuarters + "D:" + stringDimes + "N:" + stringNickels + "P:" + stringPennies);
}
;
function changeReturn() {
    $('#changeReturn').click(function () {
        makeChange();
        resetMoney();
    });
}
function resetChangeReturn() {
    $('#totalChange').empty;
}