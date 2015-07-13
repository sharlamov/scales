function calcNetto() {
	bruto = document.getElementById("formDlg1:fbruto").value;
	tara = document.getElementById("formDlg1:ftara").value;
	document.getElementById("formDlg1:fnetto").innerHTML = Math
			.round((bruto - tara) * 100) / 100;
}

function setDialogName(dialogName) {
	document.getElementById("formDlg1:editDialog_title").innerHTML = dialogName;
}


PrimeFaces.locales ['ro'] = {
	    closeText: 'Închide',
	    prevText: 'Înapoi',
	    nextText: 'Înainte',
	    currentText: 'Acasă',
	    monthNames: ['Ianuarie', 'Februarie', 'Martie', 'Aprilie', 'Mai', 'Iunie', 'Iulie', 'August', 'Septembrie', 'Octombrie', 'Noiembrie', 'Decembrie' ],
	    monthNamesShort: ['Ian', 'Feb', 'Mar', 'Apr', 'Mai', 'Iun', 'Iul', 'Aug', 'Sep', 'Oct', 'Noi', 'Dec' ],
	    dayNames: ['Duminică', 'Luni', 'Marți', 'Miercuri', 'Joi', 'Vineri', 'Sâmbătă'],
	    dayNamesShort: ['Dum', 'Lun', 'Mar', 'Mie', 'Joi', 'Vin', 'Sâm'],
	    dayNamesMin: ['D', 'L', 'M', 'Mi', 'J', 'V', 'S'],
	    weekHeader: 'Săptămâna',
	    firstDay: 1,
	    isRTL: false,
	    showMonthAfterYear: false,
	    yearSuffix: '',
	    timeOnlyTitle: 'Numai timp',
	    timeText: 'Timp',
	    hourText: 'Ora',
	    minuteText: 'Minut',
	    secondText: 'Secunde',
	    ampm: false,
	    month: 'Luna',
	    week: 'Săptămâna',
	    day: 'Zi',
	    allDayText: 'Toată ziua'
	};