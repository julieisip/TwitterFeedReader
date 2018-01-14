$(function() {
	var Feed = Backbone.Model.extend({

		urlRoot : "http://localhost:8080/twitter-feed-reader/reader",

		initialize : function() {
			this.bind("reset", function(model, options) {
				console.log("Inside event");
				console.log(model);

			});
		}

	});
	var feed = new Feed();

	feed.fetch({
		success : function(response, xhr) {
			console.log("Inside success");
			console.log(response);
		},
		error : function(errorResponse) {
			console.log(errorResponse)
		}
	});

	console.log("object:", feed);
	var TableView = Backbone.View.extend({
		el : "#container1",
		template : _.template($("#rowTemplate").html()),
		model : feed,
		initialize : function() {
			this.model.on("change", this.render, this);
		},
		render : function() {

			var template = this.template(this.model.attributes[0]);

			$("#myTable").append(template);

		}
	});

	var tableview = new TableView();
});