 function initFormCheck(formId) {
    var agt=navigator.userAgent.toLowerCase();

    // Browser Detection stuff
    this.major = parseInt(navigator.appVersion);
    this.ie     = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1));
    this.ie3    = (this.ie && (this.major < 4));
    this.ie4    = (this.ie && (this.major == 4) && (agt.indexOf("msie 4")!=-1) );
	this.iemac  = (this.ie && (agt.indexOf("mac")!=-1));

	if( !(this.iemac || ie3 || ie4) ){
		customiseInputs(formId,'radioOn','radioOff','checkboxOn','checkboxOff');
	}
}

 function addLabelProperties(f){
	if(typeof f.getElementsByTagName == 'undefined') return;
	var labels = f.getElementsByTagName("label"), label, elem, i = j = 0;
	
	while (label = labels[i++]){
		if(typeof label.htmlFor == 'undefined') return;
		elem = document.getElementById(label.htmlFor);
		//elem = f.elements[label.htmlFor]; /* old method */
		
		if(typeof elem == 'undefined'){
			//no label defined, find first sub-input
			var inputs = label.getElementsByTagName("input");
			if(inputs.length==0){
				continue;
			} else {
				elem=inputs[0];
			}
		} else if(typeof elem.label != 'undefined') { // label property already added
			continue;
		} else if(typeof elem.length != 'undefined' && elem.length > 1 && elem.nodeName != 'SELECT'){
			for(j=0; j<elem.length; j++){
				elem.item(j).label = label;
			}
		}
		elem.label = label;
	}
} 

function toggleLabelStyle(formId, label, onClass, offClass){
	if(!document.getElementById || !label) return;
		
	var form = document.getElementById(formId); //label.form;
	if(!form) return;
	
	//find radio associated with label (if in htmlFor form)
	if(label.htmlFor) {
		var e = document.getElementById(label.htmlFor);
		
		if(e.type=="checkbox"){
			e.label.className = (e.label.className==onClass) ? offClass : onClass;
			e.checked = (e.label.className==onClass);
		} else if(e.type=="radio"){
			var radioGroup = form.elements[e.name];
			if(!radioGroup) return;
			
			for(var i=0; i<radioGroup.length; i++){
				if(radioGroup[i].label){
					radioGroup[i].label.className = ((radioGroup[i].checked=(radioGroup[i].id == e.id))
													 && radioGroup[i].label) ? onClass : offClass;
				}
			}
		}
	}
}


function customiseInputs(formId, onClassRadio, offClassRadio, onClassCheckbox, offClassCheckbox) {
	if(!document.getElementById) return;

	var prettyForm = document.getElementById(formId);
	if(!prettyForm) return;
		
	//onReset, reset radios to initial values
	prettyForm.onreset = function() { customiseInputs(formId, onClassRadio, offClassRadio, onClassCheckbox, offClassCheckbox); }
	
	//attach an easy to access .label reference to form elements
	addLabelProperties(prettyForm);

	var inputs = prettyForm.getElementsByTagName('input');
	for (var i=0; i < inputs.length; i++) {
		/* NB: Yeah, i know this code is duplicated - I can't figure out how to create a local, persistent
			variable within the anonymous function calls. Fix it if you can, and let me know. */
			
		//RADIO ONLY
		if( (inputs[i].type=="radio") && inputs[i].label && onClassRadio && offClassRadio){
			//hide element
			inputs[i].style.position="absolute"; inputs[i].style.left = "-1000px";
			//initialise element
			inputs[i].label.className=offClassRadio;
			//when the label is clicked, call toggleLabelStyle and toggle the label
			inputs[i].label.onclick = function (){ toggleLabelStyle(formId, this, onClassRadio, offClassRadio); return false; };
			//enable keyboard navigation
			inputs[i].onclick = function (){ toggleLabelStyle(formId, this.label, onClassRadio, offClassRadio); };
			//if the radio was checked by default, change this label's style to checked
			if(inputs[i].defaultChecked || inputs[i].checked){ toggleLabelStyle(formId, inputs[i].label, onClassRadio, offClassRadio); }
		}
		
		//CHECKBOX ONLY
		if( (inputs[i].type=="checkbox") && inputs[i].label && onClassCheckbox && offClassCheckbox){
			//hide element
			inputs[i].style.position="absolute"; inputs[i].style.left = "-1000px";
			//initialise element
			inputs[i].label.className=offClassCheckbox;
			inputs[i].checked = false;
			//when the label is clicked, call toggleLabelStyle and toggle the label
			inputs[i].label.onclick = function (){ toggleLabelStyle(formId, this, onClassCheckbox, offClassCheckbox); return false; };
			//enable keyboard navigation
			inputs[i].onclick = function (){ toggleLabelStyle(formId, this.label, onClassCheckbox, offClassCheckbox); };
			//if the radio was checked by default, change this label's style to checked
			if(inputs[i].defaultChecked || inputs[i].checked){ toggleLabelStyle(formId, inputs[i].label, onClassCheckbox, offClassCheckbox); }
		}

		if( (inputs[i].type=="checkbox") || (inputs[i].type=="radio") && inputs[i].label ){
			//Attach keyboard navigation
			if(!this.ie){ //IE has problems with this method
				//You could set these to grab a passed in class name if you wanted to
				//do something a bit more interesting for keyboard states. But for now the
				//generic dotted outline will do for most elements.
				inputs[i].label.style.margin = "1px";
				inputs[i].onfocus = function (){ this.label.style.border = "1px dotted #333"; this.label.style.margin="0px"; return false; };
				inputs[i].onblur  = function (){ this.label.style.border = "none"; this.label.style.margin="1px"; return false; };
			}
		}
}
}
