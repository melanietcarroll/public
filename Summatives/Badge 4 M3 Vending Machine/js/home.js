$(document).ready(function () {
    loadItems();

});

function loadItems() {
    // clearItems();
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
                row += '<p>' + itemName + '</p>';
                row += '<p>' + itemPrice + '</p>';
                row += '<p>Quantity Left: ' + itemQuantity + '</p>';
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