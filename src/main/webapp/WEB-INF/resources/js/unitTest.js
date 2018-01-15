describe('Feed model', function() {

  describe('when instantiated', function() {

    it('should exhibit attributes', function() {
      var feed = new Feed({
        userName: 'Salesforce',
        userScreenName:'salesforce'
        	
      });
      expect(todo.get('user'))
        .toEqual('Salesforce');
      expect(todo.get('user'))
      .toEqual('salesforce');
    });

  });

});

describe("TableView View", function() {
	  
	  beforeEach(function() {
	    this.model = new Backbone.Model({
	      userName: 'Salesforce',
	      userScreenName: "salesforce",
	     
	    });
	    this.view = new TableView({
	      model: this.model,
	      template: this.templates.userName
	    });
	    setFixtures('<ul class="userNames"></ul>');
	  });
	  
	  it("loads the Todo template", function() {
	    expect(this.templates.Name).toBeDefined();
	  });
});
	  