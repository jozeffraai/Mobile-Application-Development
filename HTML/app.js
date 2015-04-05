function add()
{
	//Retrive the entered form data
	var rating = $('[name="rating"]').val();
	var date = $('[name="date"]').val();
	//Fetch the existing objects
	var objects = getObjects();
	//Push the new item into the existing list
	objects.push({
		rating: rating,
		date: date
	});
	//Store the new list
	saveObjects(objects);
//reload the page to show the new objects
	window.location.reload();
}

function getObjects()
{
	//see if objects is inside localStorage
	if(localStorage.getItem("objects"))
	{
		//If yes, then load objects
		objects = JSON.parse(localStorage.getItem("objects"));
	}
	else
	{
		//Make a new array of objects
		objects = new Array();
	}
	return objects;
}

function saveObjects(objects)
{
	//Save the list into localStorage
	localStorage.setItem("objects", JSON.stringify(objects));
}

function homepage()
{	
	var result = 0;
	var aantalStemmen = 0;
	if(localStorage.getItem("objects"))
	{
		objects = JSON.parse(localStorage.getItem("objects"));
	
	$.each(objects, function(index, item){
		getal = parseInt(item.rating);
		result = result + getal;
		aantalStemmen++

	});
	result = result/aantalStemmen;
	result = result.toFixed();
	
		$(".value").html("This event has a rating of "+ result+ " out of 5");
		}
	else
	{
		$(".value").html("This event has not been rated yet");
	}
}

$(document).on('pagebeforeshow', '#rootsrider', function(event) {
homepage();
});