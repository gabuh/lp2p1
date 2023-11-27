document.getElementById('addJogador').addEventListener('click', function () {
    // Get the selected jogador from the dropdown
    var selectedJogador = document.getElementById('jogadorSelect');
    var jogadorInfo = selectedJogador.options[selectedJogador.selectedIndex].text.split(' - ');
    if (jogadorInfo[0] === undefined){
        return
    }

    // Create a new row in the table
    var table = document.getElementById('jogadoresTable').getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.rows.length);
    newRow.setAttribute('data-jogador-id', jogadorInfo[4]);
    console.log(jogadorInfo)
    // Insert cells with jogador information
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);

    cell1.innerHTML = jogadorInfo[0]; // Nome
    cell2.innerHTML = jogadorInfo[1]; // Altura (you can set this value if available)
    cell3.innerHTML = jogadorInfo[2]; // Peso (you can set this value if available)
    cell4.innerHTML = jogadorInfo[3]; // Nacionalidade
});



document.getElementById('submitBtn').addEventListener('click', function () {
    // Collect data from the form fields
    var formData = new FormData(document.getElementById('timeForm'));

    // Add data from the jogador table
    var jogadoresTable = document.getElementById('jogadoresTable');
    var jogadorRows = jogadoresTable.getElementsByTagName('tbody')[0].getElementsByTagName('tr');
    for (var i = 0; i < jogadorRows.length; i++) {
       // var cells = jogadorRows[i].getElementsByTagName('td');
        formData.append('jogador_id', jogadorRows[i].getAttribute('data-jogador-id'));
    }
        console.log( jogadorRows[0].getAttribute('data-jogador-id') );


    // Send the data to the server using a POST request
    fetch('/time', {
        method: 'POST',
        body: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    })


        .then(response => {
            // Check if the response is HTML
            if (response.headers.get('content-type').toLowerCase().includes('text/html')) {
                return response.text(); // Parse as text
            } else {
                return response.json(); // Parse as JSON
            }
        })
        .then(data => {
            // Handle the response from the server
            console.log(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
});