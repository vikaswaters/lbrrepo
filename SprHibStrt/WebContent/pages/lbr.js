// Timestamp the page was last updated with
//var ajaxURL='http://localhost:8080/SprHibStrt';
var ajaxURL='http://eyaksh.com/SprHibStrt';
var lastUpdate = 0;

function detectBrowser(){  // 
	var browserName=navigator.appName; 
	if (browserName.indexOf("Microsoft")!= -1)
	{ 
		return 1; //alert("Hi Netscape User!");
	}
	else if (browserName.indexOf("Mozilla")!= -1)
	{
		return 2; //alert("Hi, Explorer User!");
	}	
	else if (browserName.indexOf("Netscape")!= -1)
	{
		return 2; //alert("Hi, Explorer User!");
	}
	else
	{
		return -1; alert("What ARE you browsing with here?");
	}
}

function getElementValue(fieldName){
	   var contents = document.getElementsByName(fieldName)[0];
	   //alert(fieldName+ '-- :  '+contents.value);
	   return contents.value;
}

function setElementValue(fieldName, newValue){
	   var contents = document.getElementsByName(fieldName)[0];
	   //alert(contents+ ':  '+contents.value);
	  // if(detectBrowser()==2)
		   contents.value = newValue;
		   //contents.value=newValue;
	   //else if(detectBrowser()==1){ // IE
		//   contents.value = newValue;
	   
}

function InitiateCalendar(fieldName, anchor, fieldToBeChanged, changeEndDateAlso){
	CallCalendarMethod(fieldName, anchor, changeEndDateAlso);
}

function AddTimeStamp(formName, fieldName){
	//alert('AddTimeStamp called');
	//alert('AddTimeStamp completed');
	oForm = document.forms[formName];
	alert('FILED -- > '+oForm.fieldName.value);
	oForm.fieldName.value = oForm.fieldName.value+' 10:00:00';
}

function SetActionSubmitForm(formName, action){
	//alert('OK1');
	oForm = document.forms[formName];
	oForm.formAction.value = action;
	oForm.submit();
}

function loadSubCategoryData(elem) {
	//alert('loadSubCategoryData  called....');
	if(elem.value ==6){
		//document.forms['EventsForm'].endDate.
		setElementValue('endDate', '3000-01-01 20:00');
	}
	var index = document.getElementsByName('category')[0].value;
	if(index>0){
		url = ajaxURL+"/UserPreference.do?action=add&ajax=yes&category="+index;
		sendAJAXGetRequest(url, updateSubcategory);
	}
}

function checkUserNameAvailability() {
	//alert(detectBrowser());
	//alert('checkUserNameAvailability  called....');
	//document.getElementsByName("unameavailability")[0].setAttribute("type", "hidden");
	var elem = document.getElementById("unameavailability");
	
   if(detectBrowser()==2)
	   elem.firstChild.nodeValue=" ";
   else if(detectBrowser()==1){
	   elem.value=" ";
   }	
	
	elem.removeAttribute("style");
	var potentialUserName = document.getElementsByName('userid')[0].value;
	if(potentialUserName!=""){
		url = ajaxURL+"/UserRegister.do?action=add&ajax=yes&userName="+potentialUserName;
		//alert('Ajax Request--> '+url);
		sendAJAXGetRequest(url, displayUserNameAvailability);
	}
}

function sendAJAXGetRequest(url, methodToBeInvokedAfterAjax){
	 var req = newXMLHttpRequest();
     //alert(req.toString());
	 req.onreadystatechange = getReadyStateHandler(req, methodToBeInvokedAfterAjax);
	 //req.open("GET", "lbr?action=add", true);  //  relative urls  do not work ??
	 req.open("GET", url, true);	 
	 req.send(null);
	 
	//for POST
	//url = ajaxURL+"/UserPreference.do";
    //postparams="action=add&item=6&category="+index;
}

function sendAJAXPostRequest(url, methodToBeInvokedAfterAjax, postparams){
	  var req = newXMLHttpRequest();
	  req.open("POST", url, true);
	  req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	  req.send(postparams);
}

/**
 * Update the page with the Ajax XML response
 * @param lbrXML
 */
function updateSubcategory(lbrXML) {
	//alert('updateCategory()  called...  XML==>!!'+lbrXML);
	var lbr = lbrXML.getElementsByTagName("lbr")[0];
	if(alert==null)
		alert('Seems like ajax getting served from cache... clear cache');
	var generated = lbr.getAttribute("generated");
	//alert('generated-> '+generated);
	if (generated > lastUpdate) {
		lastUpdate = generated;
		var contents = document.getElementsByName('subcategory')[0];
		contents.innerHTML = "";
		//alert('contents.innerHTML-> '+contents.innerHTML);
		var subCatNames = lbr.getElementsByTagName("subcategory")[0].getElementsByTagName("name");
		var subCatIDs = lbr.getElementsByTagName("subcategory")[0].getElementsByTagName("catid");
		// alert('numElems='+items.length);
		for (var I = 0 ; I < subCatNames.length ; I++) {
			var subCatName = subCatNames[I].firstChild.nodeValue;
			var subCatID = subCatIDs[I].firstChild.nodeValue;
			// alert(subcat);
			//var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
			//var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;
			var attr = document.createAttribute('value');
			attr.nodeValue  = subCatID;
			AddChild(document, contents, "option", subCatName, attr);
		}
	}
}

function displayUserNameAvailability(lbrXML) {
	//alert(navigator.appName);
	 //alert('displayUserNameAvailability()  called...  XML==>!!'+lbrXML);
	// alert('lbrXML.getElementsByTagName("usernameAvail")[0]-->' +lbrXML.getElementsByTagName("usernameAvail")[0]);
	 var answer = lbrXML.getElementsByTagName("usernameAvail")[0].getElementsByTagName("avail")[0];
	 //alert('Answer= '+answer.firstChild.nodeValue);
	  // var contents = document.getElementsByName("unameavailability")[0];
	 var attr = document.createAttribute('src'); // for success failure ICON image (e.g <img height="15" width="15" src="/images/success.png">)
	 // getElementById works in IE only if the name and id attribute of the element is the same. strange but thats  the way it works
	 var contents = document.getElementById("unameavailability");
	 //alert(document.getElementsByTagName("IMG").length);
	if(document.getElementsByTagName("IMG")!=null && document.getElementsByTagName("IMG").length > 0)
		 contents.removeChild(document.getElementsByTagName("IMG")[0]);
	 //alert('index of NOT->'+answer.firstChild.nodeValue.indexOf("NOT"));
	 if(answer.firstChild.nodeValue.indexOf("NOT") == -1){ // success
		 //alert('make it red->'+ answer.firstChild.nodeValue.indexOf("NOT"));
		 attr.nodeValue  = "/images/success.png";
		 if(detectBrowser()==1) // IE special
			 contents.style.setAttribute('cssText', "color:  #347C2C"); 
		 else
			 contents.setAttribute("style", "color:  #347C2C");
	 }
	 else{ 
		 //alert('make it green->'+ answer.firstChild.nodeValue.indexOf("NOT"));
		 attr.nodeValue  = "/images/error.png";
		 if(detectBrowser()==1)
			 contents.style.setAttribute('cssText', "color:  #FF0000");
		 else		 
			 contents.setAttribute("style", "color:  #FF0000");
	 }
	   //alert('contents= '+contents.nodeValue);
	   //contents.innerHTML = "";
		
	  AddChild(document, contents, "IMG", null, attr);
	   if(detectBrowser()==2)
		   contents.firstChild.nodeValue=answer.firstChild.nodeValue;
		   //contents.firstChild.nodeValue=" <img height=/"15/" width=/"15/" src=/"/images/success.png/">/" + answer.firstChild.nodeValue;
	   else if(detectBrowser()==1){ // IE
		   contents.innerHTML = answer.firstChild.nodeValue;
	   }
	}

/*function updateSubcategoryOrig(lbrXML) {
	alert('updateSubcategoryOrig()  called !!');
 var lbr = lbrXML.getElementsByTagName("lbr")[0];
 var generated = lbr.getAttribute("generated");
 if (generated > lastUpdate) {
   lastUpdate = generated;
   var contents = document.getElementById("contents");
   contents.innerHTML = "";

   var items = lbr.getElementsByTagName("item");
   for (var I = 0 ; I < items.length ; I++) {

     var item = items[I];
     var name = item.getElementsByTagName("name")[0].firstChild.nodeValue;
     var quantity = item.getElementsByTagName("quantity")[0].firstChild.nodeValue;

     var listItem = document.createElement("li");
     listItem.appendChild(document.createTextNode(name+" x "+quantity));
     contents.appendChild(listItem);
   }

 }
 document.getElementById("total").innerHTML = lbr.getAttribute("total");
}*/

function AddChild(DOC, PARENT, CHILD, CHILDTEXT, attribute){
	var ChildElement;
	if (typeof(CHILD) == "string"){
		ChildElement = DOC.createElement(CHILD);
	}else{
		ChildElement = CHILD;
	}
	if (typeof CHILDTEXT != "undefined" && CHILDTEXT != null){
		var ChildText = DOC.createTextNode(CHILDTEXT);
		ChildElement.appendChild(ChildText);
	}
	if (typeof attribute != "undefined"){
			ChildElement.setAttributeNode(attribute); 
	}
	//alert('step6');
	PARENT.appendChild(ChildElement);
	return ChildElement;
}
