describe('ageranger UI regression suite', function() {
	
		
	it('AR_TC_001 - Add a new person in ageranger DB', function() {
		
    browser.get('http://ageranger.azurewebsites.net');
	browser.driver.sleep(1000);
    element(by.css('[ng-click="openNewPersonForm()"]')).click();	
		
    this.selectWindow = function (index) {
         browser.driver.wait(function() {
        return browser.driver.getAllWindowHandles().then(function (handles) {   
            if(handles.length > index) {
              return true;
            }
          });
      });
      // switch to 1st popup window
      return browser.driver.getAllWindowHandles().then(function (handles) {
      return browser.driver.switchTo().window(handles[index]);
      });
    };
	
	element(by.model("person.FirstName")).click();
	element(by.model("person.FirstName")).sendKeys("Pranathi");	
	element(by.model("person.LastName")).click();
	element(by.model("person.LastName")).sendKeys("Karre");
	element(by.model("person.Age")).click();
	element(by.model("person.Age")).sendKeys("19");
	

	// submit button is not working here
	element(by.css('[ng-click="submit()"]')).click();
		
	this.selectWindow = function (index) {
         browser.driver.wait(function() {
        return browser.driver.getAllWindowHandles().then(function (handles) {   
            if(handles.length > index) {
              return true;
            }
          });
      });
      // switch to 2nd popup window
      return browser.driver.getAllWindowHandles().then(function (handles) {
      return browser.driver.switchTo().window(handles[index]);
      });
    };
	
	//Issue with locator and second popup OK button is not working. 
	element(by.css('.btn btn-primary')).click();
	//browser.driver.sleep(3000);
	
	var elm = element.all(by.repeater("person in people  | filter:search"));
    elm.each(function(obj, index) {
    obj.getText().then(function (text) {
    console.log(index, text);
	});
	}); 
	
	});
   
   
   //////////////////////////////////////////////////////////////////////////////////
    
     it('AR_TC_002 - Display the details of the list of persons in DB', function() {

    browser.get('http://ageranger.azurewebsites.net');
    browser.driver.sleep(1000);
	
	var elm = element.all(by.repeater("person in people  | filter:search"));
    elm.each(function(obj, index) {
    obj.getText().then(function (text) {
    console.log(index, text);
	});
	});
  
  }); 
      
  //////////////////////////////////////////////////////////////////////////////////
	
    it('AR_TC_003 - Search the person by first name', function() {

    browser.get('http://ageranger.azurewebsites.net');

    element(by.id("searchText")).sendKeys("test");
	browser.driver.sleep(1000);
  
	var elm = element.all(by.repeater("person in people  | filter:search"));
    elm.each(function(obj, index) {
    obj.getText().then(function (text) {
    console.log(index, text);
	expect(text).toBe('test test 98 Old');
	});
	});
  
  });
  
  //////////////////////////////////////////////////////////////////////////////////
  
  it('AR_TC_004 - Search the person by last name', function() {

    browser.get('http://ageranger.azurewebsites.net');

    element(by.id("searchText")).sendKeys("test");
	browser.driver.sleep(1000);
     
	var elm = element.all(by.repeater("person in people  | filter:search"));
   elm.each(function(obj, index) {
    obj.getText().then(function (text) {
    console.log(index, text);
	expect(text).toBe('test test 98 Old');
	});
	});
  
  }); 
    
   //////////////////////////////////////////////////////////////////////////////////
  
   it('AR_TC_005 - Delete a person from ageranger DB', function() {

    browser.get('http://ageranger.azurewebsites.net');
	browser.driver.sleep(1000);
	element(by.css('[ng-click="delete(person)"]')).click();
	browser.driver.sleep(3000);
	
	this.selectWindow = function (index) {
         browser.driver.wait(function() {
        return browser.driver.getAllWindowHandles().then(function (handles) {   
            if(handles.length > index) {
              return true;
            }
          });
      });
      // switch to popup window
      return browser.driver.getAllWindowHandles().then(function (handles) {
      return browser.driver.switchTo().window(handles[index]);
      });
    };
		
	element(by.css('.btn-primary')).click();
	var elm = element.all(by.repeater("person in people  | filter:search"));
   
   elm.each(function(obj, index) {
    obj.getText().then(function (text) {
    console.log(index, text);
	});
	});
		
  });   
   //////////////////////////////////////////////////////////////////////////////////
  
    it('AR_TC_006 - Update the firstname of a person', function() {

    browser.get('http://ageranger.azurewebsites.net');
	   
	browser.driver.sleep(1000);
    element(by.css('[ng-click="openEditForm(person)"]')).click();	
	browser.driver.sleep(3000);
	
	this.selectWindow = function (index) {
         browser.driver.wait(function() {
        return browser.driver.getAllWindowHandles().then(function (handles) {   
            if(handles.length > index) {
              return true;
            }
          });
      });
      // switch to popup window
      return browser.driver.getAllWindowHandles().then(function (handles) {
      return browser.driver.switchTo().window(handles[index]);
      });
    };
	
	
	element(by.model("person.FirstName")).clear();
	element(by.model("person.FirstName")).sendKeys("Meher");
	element(by.css('[ng-click="submit()"]')).click();

	
	this.selectWindow = function (index) {
         browser.driver.wait(function() {
        return browser.driver.getAllWindowHandles().then(function (handles) {   
            if(handles.length > index) {
              return true;
            }
          });
      });
      // switch to popup window
      return browser.driver.getAllWindowHandles().then(function (handles) {
      return browser.driver.switchTo().window(handles[index]);
      });
    };
			
	//Issue with locator and second popup OK button is not working. 
	element(by.css('.btn btn-primary')).click();
		
	var elm = element.all(by.repeater("person in people  | filter:search"));
    elm.each(function(obj, index) {
    obj.getText().then(function (text) {
    console.log(index, text);
	});
	});
  }); 
   //////////////////////////////////////////////////////////////////////////////////
     
 
  });
