var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function updateTable() {
       $.ajax({
                type: "POST",
                url: ajaxUrl + "filter",
                data: $("#filter").serialize(),
                success: updateTableByData
        });
    }

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "dateTime": "dateTime"
            },
            {
                "description": "description"
            },
            {
                "calories": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();
});

