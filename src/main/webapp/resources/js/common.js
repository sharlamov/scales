function calcNetto() {
	bruto = document.getElementById("formDlg1:fbruto").value;
	tara = document.getElementById("formDlg1:ftara").value;
	document.getElementById("formDlg1:fnetto").innerHTML = Math
			.round((bruto - tara) * 100) / 100;
}

function setDialogName(dialogName) {
	document.getElementById("formDlg1:editDialog_title").innerHTML = dialogName;
}