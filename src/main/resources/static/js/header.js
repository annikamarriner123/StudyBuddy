/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */


document.write(`
<header>
    <div class="container">
        <div class="banner">
            <h2><a href="/home">Study Buddy</a></h2>
            <a href="/findStudyPeers">Find Study Peers</a>
            <a href="/team">Teams</a>
            <a href="/chat">Inbox</a>
            <a href="/updatePapers">Add Papers</a>
            <a>Welcome ${authenticatedUser}</a>
            <div class="dropdown">
                <button class="dropbtn">Menu</button>
                <div class="dropdown-content">
                    <a href="/update-details">Account Info</a>
                    <a href="/logout" id="log-out">Log Out</a>
                    <a href="/home">Home</a>
                </div>
            </div>
        </div>
    </div>
</header>
`);
