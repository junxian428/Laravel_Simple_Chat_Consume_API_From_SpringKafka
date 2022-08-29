<html>
<pre id="arrPrint"></pre>
<div class="container"></div>

<input type="text" id="chat" name="chat"><br>
 <input type="button" value="Upload File" onclick="uploadFile()">

 <script>
const userAction = async () => {
  const response = await fetch('http://localhost:8888/kafkaapp/chat');
  const myJson = await response.json(); //extract JSON from the http response
  let arr = myJson;
  arr = arr.join("\n");
  //arr = arr.replace(",","");
  document.getElementById("arrPrint").innerHTML = arr;
  //console.log(myJson);
  // do something with myJson
}
function uploadFile(){
    var chat = document.getElementById("chat").value;
    //alert(chat);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8888/kafkaapp/post?msg=" + chat );
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.onload = () => console.log(xhr.responseText);
    xhr.send();
}
//getUsers() 
  //window.setInterval('refresh()', 10000); 	
    // Call a function every 10000 milliseconds 
    // (OR 10 seconds).

    // Refresh or reload page.
//function refresh() {
    //window .location.reload();
//}
userAction();

setTimeout(function() {
  location.reload();
}, 2000);

</script>

</html>