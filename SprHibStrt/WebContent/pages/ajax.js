/*
 * Returns an new XMLHttpRequest object, or false if the browser
 * doesn't support it
 */
function newXMLHttpRequest() {

  var xmlreq = false;

  // Create XMLHttpRequest object in non-Microsoft browsers (// Opera 8.0+, Firefox, Safari)
  if (window.XMLHttpRequest) {
	// alert('non MS Browser');
    xmlreq = new XMLHttpRequest();

  } else if (window.ActiveXObject) {
    try {
      // Try to create XMLHttpRequest in later versions
      // of Internet Explorer
      xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
     //alert('MS Browser');
    } catch (e1) {

      // Failed to create required ActiveXObject
      
      try {
        // Try version supported by older versions
        // of Internet Explorer
      
        xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
        alert('Old MS Browser');
      } catch (e2) {

        // Unable to create an XMLHttpRequest by any means
        xmlreq = false;
        alert('AJAX req failed');
      }
    }
  }

return xmlreq;
}

 /*
	* Returns a function that waits for the specified XMLHttpRequest
	* to complete, then passes it XML response to the given handler function.
  * req - The XMLHttpRequest whose state is changing
  * responseXmlHandler - Function to pass the XML response to
  */
 function getReadyStateHandler(req, responseXmlHandler) {
	 //alert('getReadyStateHandler()  called !!');
   // Return an anonymous function that listens to the XMLHttpRequest instance
   return function () {

     // If the request's status is "complete"
     if (req.readyState == 4) {
    	 //alert('req.readyState == 4');
       // Check that we received a successful response from the server
       if (req.status == 200) {
    	   //alert('req.status == 200');
         // Pass the XML payload of the response to the handler function.
         responseXmlHandler(req.responseXML);

       } else {

         // An HTTP problem has occurred
         alert("HTTP error "+req.status+": "+req.statusText);
       }
     }
   }
 }
