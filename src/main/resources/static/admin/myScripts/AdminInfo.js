const meUrl = "http://localhost:8080/api/rest/user";

fetch(meUrl).then(
    res => {
        res.json().then(
            data => {

                document.getElementById("activeUserInfoInAdmin").innerHTML = `   
                    <tr>
                    <td>${data.id}</td>
                    <td>${data.email}</td>
                    <td>${data.username}</td>
                    <td>${data.age}</td>
                    <td>${data.username}</td>
                    <td>${data.userRole}</td>
                    </tr>
                    `;
            }
        )


    }
)

