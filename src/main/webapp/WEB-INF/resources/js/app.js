$(function() {

	// model get data from java rest application /reader endpoint
	var Feed = Backbone.Collection.extend({

		url : "reader",

		initialize : function() {
			this.bind("reset", function(model, options) {
				console.log("Inside event");
				console.log(model);

			});
		},
		search : function(search) {
			if (search == "")
				return this;

			var pattern = new RegExp(search, "gi");
			return _(this.filter(function(data) {
				return pattern.test(data.get("content"));
			}));
		}

	});

	var feed = new Feed();

	//fetch data
	feed.fetch({
		success : function(response, xhr) {
			console.log("Inside success");
			console.log(response);
		},
		error : function(errorResponse) {
			console.log(errorResponse)
		}
	});

	//refresh every minute
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

	}, 60000);

	//feed View
	var FeedListView = Backbone.View.extend({

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
		collection : feed,
		initialize : function() {
			this.template = _.template($("#feed-template").html());
			this.collection.on("add", this.render, this);

		},
		render : function(data) {

			var feed;
			$("#feed-list").html("");

			this.collection.each(function(feed) {

				var view = new FeedItemView({
					model : feed,
					collection : this.collection

				});

				$("#feed-list").append(view.render().el);

			});
			return this;

		},
		renderList : function(data) {

			var feed;
			$("#feed-list").html("");

			data.each(function(feed) {

				var view = new FeedItemView({
					model : feed,
					collection : this.collection

				});

				$("#feed-list").append(view.render().el);

			});
			return this;

		},
		search : function(event) {

			var query = $('#searchFeed').val();
			console.log(query);
			this.renderList(this.collection.search(query));
		}

	});

	var FeedItemView = Backbone.View.extend({

		events : {},
		render : function(data) {
			var template = this.template(this.model.attributes);
			$(this.el).html(template);
			return this;
		},
		initialize : function() {
			this.template = _.template($("#feed-template").html());
		}
	});

	var feedListView = new FeedListView();

	Backbone.history.start();

});