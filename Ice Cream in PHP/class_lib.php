<?php

class globalValuesClass {
  var $subtotal = 0.00;

  function __construct() {}

  public function get_subtotal() {
    return $this->subtotal;
  }//get_cost

  protected function set_subtotal($new_subtotal) {
     $this->subtotal = $new_subtotal;
  }//set_cost
}

class dessert {

	var $dessert_type;
  var $scoop_count;
  var $flavor = array();
  var $vessel;
  var $cost = 3.00;

	function __construct($dessert_type) {
		$this->$dessert_type = $dessert_type;
	}

  public function __toString() {
    if(is_null($this->dessert)) {
      return 'NULL';
    }
    return $the_string;
  }

	public function get_dessert_type() {
		return $this->dessert_type;
	}//get_dessert_type

	protected function set_dessert_type($new_dessert_type) {
	   $this->dessert_type = $new_dessert_type;
	}//set_dessert_type

  public function get_scoop_count() {
    return $this->scoop_count;
  }//get_scoop_count

  protected function set_scoop_count($new_scoop_count) {
     $this->scoop_count = $new_scoop_count;
  }//set_scoop_count

  public function get_flavor() {
    return $this->flavor;
  }//get_flavor

  protected function set_flavor($new_flavor) {
     $this->flavor = $new_flavor;
  }//set_flavor

  public function get_vessel() {
    return $this->vessel;
  }//get_vessel

  protected function set_vessel($new_vessel) {
     $this->vessel = $new_vessel;
  }//set_vessel

  public function get_cost() {
    return $this->cost;
  }//get_cost

  protected function set_cost($new_cost) {
     $this->cost = $new_cost;
  }//set_cost
}//dessert

class icecream extends dessert {
	function __construct($dessert_type) {
		$this->set_dessert_type($dessert_type);
	}
}//icecream

class milkshake extends dessert {
  var $milk;
  var $cost = 2.00;

  function __construct($dessert_type) {
		$this->set_dessert_type($dessert_type);
    $this->set_vessel("cup");
	}

  public function get_milk() {
    return $this->milk;
  }//get_milk

  protected function set_milk($new_milk) {
     $this->milk = $new_milk;
  }//set_milk
}//milkshake

class float extends dessert {
  var $soda;
  var $cost = 2.00;

  function __construct($dessert_type) {
		$this->set_dessert_type($dessert_type);
    $this->vessel = "cup";
	}
  public function get_soda() {
    return $this->soda;
  }//get_soda

  protected function set_soda($new_soda) {
     $this->soda = $new_soda;
  }//set_soda
}//float




?>
