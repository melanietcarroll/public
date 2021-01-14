$(document).ready(function() {
    loadDvds();
});

function loadDvds() {
    clearDvdTable()
    var contentRows = $('#contentRows');
    
    $.ajax({
        type: 'GET',
        url: 'https://tsg-dvds.herokuapp.com/dvds',
        success: function(dvdArray) {
            $.each(dvdArray, function(index, dvd){
                var title = dvd.title;
                var releaseYear = dvd.releaseYear;
                var director = dvd.director;
                var rating = dvd.rating;
                var notes = dvd.notes;
                var dvdId = dvd.id;

                var row = '<tr>';
                row += '<td id="title">' + '<u>' + title + '<u>' + '</td>';
                row += '<td>' + releaseYear + '</td>';
                row += '<td>' + director + '</td>';
                row += '<td>' + rating + '</td>';
                row += '<td><button type="button" class="btn btn-info" id="editButton" onclick="showEditDvdForm(' + dvdId + ')">Edit</button></td>';
                row += '<td><button type="button" class="btn btn-danger" id="deleteButton" onclick="deleteDvd(' + dvdId + ')">Delete</button></td>';
                row += '</tr>';

                contentRows.append(row);
            });

        },
        error: function () {
            $('#errorMessages')
                    .append($('<li>')
                            .attr({class: 'list-group-item list-group-item-danger'})
                            .text('Error calling web service. Please try again later.'));
        }
    });
}
function clearDvdTable() {
    $('#contentRows').empty();
    }
    
    function addDvd() {
    $('#createDvd').click(function(event) {
        $.ajax({
           type: 'POST',
           url: 'https://tsg-dvds.herokuapp.com/dvd',
           data: JSON.stringify({
                title: $('#createDvdTitle').val(),
                releaseYear: $('#createReleaseYear').val(),
                director: $('#createDirector').val(),
                rating: $('#createRating').val(),
                notes: $('#createNotes').val()
           }),
           headers: {
               'Accept': 'application/json',
               'Content-Type': 'application/json'
           },
           'dataType': 'json',
           success: function() {
               $('#errorMessages').empty();
               $('#createDvdTitle').val('');
               $('#createReleaseYear').val('');
               $('#createDirector').val('');
               $('#createRating').val('');
               $('#createNotes').val('');
               loadDvds();
           },
           error: function () {
               $('#errorMessages')
                .append($('<li>')
                .attr({class: 'list-group-item list-group-item-danger'})
                .text('Error calling web service. Please try again later.')); 
           }
        });
    });
}

function showCreateDvdForm() {
    $('#errorMessages').empty();
    
    $('#dvdTableInfo').hide();
    $('#createDVD').show();
}

function hideCreateDvdForm(){
    $('#errorMessages').empty();
    
    $('#createDvdTitle').val('');
    $('#createReleaseYear').val('');
    $('#createDirector').val('');
    $('#createRating').val('');
    $('#createNotes').val('');

    $('#dvdTableInfo').show();
    $('#createDVD').hide();
}

