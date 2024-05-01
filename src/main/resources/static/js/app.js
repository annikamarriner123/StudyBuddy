/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
document.addEventListener('DOMContentLoaded', function() {
    var searchButton = document.getElementById("searchButton");
    if (searchButton) {
        searchButton.addEventListener("click", searchUsers);
    } else {
        console.error('Search button not found.');
    }
});

function searchUsers() {
    var paperInput = document.getElementById("papers");
    if (paperInput) {
        var paper = paperInput.value;
        fetch('http://localhost:8080/searchUsers?paper=' + paper)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json(); // Parse response as JSON
            })
            .then(data => {
                var userDetailsTable = document.getElementById("userTable").getElementsByTagName('tbody')[0];
                userDetailsTable.innerHTML = ""; // Clear existing table rows
                if (data && data.firstName && data.surname) {
                    var row = userDetailsTable.insertRow();
                    row.innerHTML = `
                        <td>${data.firstName}</td>
                        <td>${data.surname}</td>
                    `; // Insert the firstName and surname into the table
                } else {
                    userDetailsTable.innerHTML = `<tr><td colspan="2">No user found with the specified paper.</td></tr>`;
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        console.error('Paper input element not found.');
    }
}