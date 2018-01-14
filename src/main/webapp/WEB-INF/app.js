$(function() {
    var Feed = Backbone.Collection.extend({
    	
    	model: feed,
        url : "/reader"
    });
 
    var feed = new Feed();
    console.log("object:", feed);
 
    var TableView = Backbone.View.extend({
        el : "#container1",
        template : _.template($("#rowTemplate").html()),
        model : feed,
        initialize : function() {
            this.model.on("change", this.render, this);
        },
        render : function() {
 
            var template = this.template(this.model.attributes);
            $("#myTable").append(template);
            "name" : $("#name").val(),
            "year" : $("#year").val(),
            
        }
    }

    var data = this.model.fetch(params);
        },
    
    });
 
    var tableview = new TableView();
});