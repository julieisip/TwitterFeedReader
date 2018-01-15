$(function() {

	// model get data from java rest application /reader endpoint
	var Feed = Backbone.Model.extend({

		urlRoot : "reader",

		initialize : function() {
			this.bind("reset", function(model, options) {
				console.log("Inside event");
				console.log(model);

			});
		},
		search : function(search) {

			// if no input return all object
			if (search == "") {
				return this;
			}

			// search in content object remove object that does not contain
			// string
			var pattern = new RegExp(search, 'gi');
			var list = new Array();

			for (i in this.attributes) {
				var model = this.attributes[i];

				console.log(this.attributes[i].content);
				console.log(pattern.test(model.content));

				if (pattern.test(model.content)) {

					list.push(model);
				}

			}
			// assign new list of feed to attribute object of model
			this.attributes = list;

			return this;
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

	setInterval(function() {
		feed.fetch({
			success : function(response, xhr) {
				console.log("Inside success");
				console.log(response);
			},
			error : function(errorResponse) {
				console.log(errorResponse)
			}
		});

	}, 6000);
	

	var TableView = Backbone.View.extend({

		events : {
			"keyup #searchFeed" : "search",
			initialize : function() {
				_.bindAll(this, 'search');
				this.remove();
				this.unbind();
	

			}
		},
		el : "#feed-container",
		template : _.template($("#feed-template").html()),
		model : feed,
		initialize : function() {

			this.model.on("change", this.render, this);
			this.model.on("keyup", this.search);
			//this.model.on("reset", this.render, this);
		},
		render : function(data) {

			var feed;
			$("#feed-list").html("");

			for (feed in this.model.attributes) {

				var view = new FeedItemView({
					model : this.model.attributes[feed],

				});

				$("#feed-list").append(view.render().el);

			}
			return this;

		},
		search : function(event) {

			var query = $('#searchFeed').val();
			console.log(query);
			this.render(this.model.search(query));
		}

	});

	var FeedItemView = Backbone.View.extend({

		events : {},
		render : function(data) {
			var template = this.template(this.model);
			$(this.el).html(template);
			return this;
		},
		initialize : function() {
			this.template = _.template($("#feed-template").html());
		}
	});

	var tableview = new TableView();

});