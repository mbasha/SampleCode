<?php include("class_lib.php"); ?>
<?php

/*
*  Mohammed Abrar Basha's Blue Acorn Project
*  5/23/2016
*
*  This application is an command line based dessert shop
*  where you follow through the cashier's instructions
*  to create an order.
*
*  To run this application:
*      cd to the folder that this file is located in and
*      input: 'php index.php'
*
*  Things to note:
*      There are many instances where I just list out some stuff
*      (like flavors for example). This could be put in an array
*      and accessed accordingly, but for something this small
*      that's a waste of space.
*
*/

  //main()
  $globalValues = new globalValuesClass();
  $iceCreamConeVar = new icecream("vanilla ice cream cone");
  $iceCreamConeVar->scoop_count = 1;
  $iceCreamConeVar->flavor = array_insert($iceCreamConeVar->flavor, "Vanilla", 1);
  $iceCreamConeVar->vessel = "cone";

  $milkshakeVar = new milkshake("chocolate milkshake");
  $milkshakeVar->scoop_count = 1;
  $milkshakeVar->flavor = array_insert($milkshakeVar->flavor, "Chocolate", 1);

  $floatVar = new float("root beer float");
  $floatVar->scoop_count = 1;
  $floatVar->flavor = array_insert($floatVar->flavor, "Vanilla", 1);

  echo "Welcome to Ye Ole Basha Ice Cream Cafe\n\n";
  echo "Here are today's specials:\n";

  echo "Special 1: $iceCreamConeVar->dessert_type\n";
    echo "\tscoops: $iceCreamConeVar->scoop_count\n";
    echo "\tflavors: " . $iceCreamConeVar->flavor[0] . "\n";
    echo "\tvessel: $iceCreamConeVar->vessel\n";
    echo "\tcost: $"; echo number_format((float)$iceCreamConeVar->cost, 2, '.', ''); echo " \n";

  echo "Special 2: $milkshakeVar->dessert_type \n";
    echo "\tscoops: $milkshakeVar->scoop_count\n";
    echo "\tflavors: " . $milkshakeVar->flavor[0] . "\n";
    echo "\tvessel: $milkshakeVar->vessel\n";
    echo "\tcost: $"; echo number_format((float)$milkshakeVar->cost, 2, '.', ''); echo " \n";

  echo "Special 3: $floatVar->dessert_type \n";
    echo "\tscoops: $floatVar->scoop_count\n";
    echo "\tflavors: " . $floatVar->flavor[0] . "\n";
    echo "\tvessel: $floatVar->vessel\n";
    echo "\tcost: $"; echo number_format((float)$floatVar->cost, 2, '.', ''); echo " \n";

  echo "4: Or create your own! Base cost is $3.00.\n";

  echo "Today we have a deal on milkshakes and foats! $1.00 off! Wow!\n\n\n\n";

  cashier($globalValues);
  echo "Do you want to add more to your order? Press 1 for yes and 2 for no.\n";
  $input = fgets(STDIN);
  settype($input, "integer");
  while($input > 2 || $input < 1){
    echo "Incorrect input. Do you want to add more to your order? Press 1 for yes and 2 for no.\n";
    $input = fgets(STDIN);
    settype($input, "integer");
  }
  while($input != 2){
    cashier($globalValues);
    echo "Do you want to add more to your order? Press 1 for yes and 2 for no.\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    while($input > 2 || $input < 1){
      echo "Incorrect input. Do you want to add more to your order? Press 1 for yes and 2 for no.\n";
      $input = fgets(STDIN);
      settype($input, "integer");
    }
  }

  echo "Your total is: $"; echo number_format((float)$globalValues->subtotal, 2, '.', ''); echo " \n";
  echo "Have a nice day!\n";
  //main() -> end

  function array_insert(&$array, $value, $index)
  {
      return $array = array_merge(array_splice($array, max(0, $index - 1)), array($value), $array);
  }

  function print_order(&$dessertIn){
    echo "Your order is: $dessertIn->dessert_type\n";
      echo "\tscoops: $dessertIn->scoop_count\n";
      echo "\tflavors:";
      for($x = 0; $x < $dessertIn->scoop_count-1; $x++)
        echo " " . $dessertIn->flavor[$x] . ", ";
        echo " " . $dessertIn->flavor[$x] . " \n";
      if($dessertIn->soda != NULL)
        echo "\tsoda: $dessertIn->soda\n";
      if($dessertIn->milk != NULL)
        echo "\tmilk: $dessertIn->milk\n";
      echo "\tvessel: $dessertIn->vessel\n";
      echo "\tcost: $"; echo number_format((float)$dessertIn->cost, 2, '.', ''); echo " \n";
  }

  function makeSpecial($specialNumber){
    $returnSpecial;
    if($specialNumber == 1){
      $returnSpecial = new icecream("vanilla ice cream cone");
      $returnSpecial->scoop_count = 1;
      $returnSpecial->flavor = array_insert($returnSpecial->flavor, "Vanilla", 0);
      $returnSpecial->vessel = "cone";
    }
    elseif($specialNumber == 2){
      $returnSpecial = new milkshake("chocolate milkshake");
      $returnSpecial->scoop_count = 1;
      $returnSpecial->flavor = array_insert($returnSpecial->flavor, "Chocolate", 0);
    }
    else {
      $returnSpecial = new float("root beer float");
      $returnSpecial->scoop_count = 1;
      $returnSpecial->flavor = array_insert($returnSpecial->flavor, "Vanilla", 0);
    }
    return $returnSpecial;
  }

  function makeIceCream(&$globalValuesIn){
    echo "Ice cream! Nice!\n";
    $customIceCream = new icecream("custom ice cream");
    //get number of scoops
    echo "How many scoops, 1 or 2? Each one is $0.50\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    while($input > 2 || $input < 1){
      echo "Incorrect input. How many scoops, 1 or 2? Each one is $0.50\n";
      $input = fgets(STDIN);
      settype($input, "integer");
    }
    $customIceCream->scoop_count = $input;
    //get scoop flavors
    $x = 0;
    for($x = 0; $x < $customIceCream->scoop_count; $x++){
      $displayX = $x+1;
      echo "What flavor would you like for scoop $displayX? 1 for Vanilla, 2 for Chocolate, 3 for Strawberry, 4 for Cookies and Cream, and 5 for Rocky Road.\n";
      $input = fgets(STDIN);
      settype($input, "integer");
      while($input > 5 || $input < 1){
        echo "Incorrect input. What flavor would you like for scoop $displayX. 1 for Vanilla, 2 for Chocolate, 3 for Strawberry, 4 for Cookies and Cream, and 5 for Rocky Road.\n";
        $input = fgets(STDIN);
        settype($input, "integer");
      }
      if(is_numeric($input) && $input > 0 && $input < 6) {
        switch ($input){
          case 1:
            $customIceCream->flavor = array_insert($customIceCream->flavor, "Vanilla", $x);
            break;
          case 2:
            $customIceCream->flavor = array_insert($customIceCream->flavor, "Chocolate", $x);
            break;
          case 3:
            $customIceCream->flavor = array_insert($customIceCream->flavor, "Strawberry", $x);
            break;
          case 4:
            $customIceCream->flavor = array_insert($customIceCream->flavor, "Cookies and Cream", $x);
            break;
          case 5:
            $customIceCream->flavor = array_insert($customIceCream->flavor, "Rocky Road", $x);
            break;
          default:
            echo "Something weird happened.\n";
        }
      }
    }
    //get vessel
    echo "What do you want your ice cream in? Press 1 for cup ($0.25) or press 2 for cone ($0.50)\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    while($input > 2 || $input < 1){
      echo "Incorrect input. What do you want your ice cream in? Press 1 for cup ($0.25) or press 2 for cone ($0.50)\n";
      $input = fgets(STDIN);
      settype($input, "integer");
    }
    if($input == 1)
      $customIceCream->vessel = "cup";
    else
      $customIceCream->vessel = "cone";
    //calculate prices
    if(strcmp($customIceCream->vessel, "cup") == 0){
        $customIceCream->cost += ($customIceCream->scoop_count * 0.5) + 0.25;
    }
    if(strcmp($customIceCream->vessel, "cone") == 0){
        $customIceCream->cost += ($customIceCream->scoop_count * 0.5) + 0.50;
    }

    //output order
    print_order($customIceCream);
    $globalValuesIn->subtotal += $customIceCream->cost;
  }

  function makeMilkshake(&$globalValuesIn){
    echo "Milkshake! My favorite! Milkshakes come in a cup, which costs $0.25\n";
    $customMilkshake = new milkshake("custom milkshake");
    $customMilkshake->scoop_count = 1;

    //get scoop flavors
    $x = 0;
    for($x = 0; $x < $customMilkshake->scoop_count; $x++){
      $displayX = $x+1;
      echo "What flavor would you like for scoop $displayX? 1 for Vanilla, 2 for Chocolate, 3 for Strawberry, 4 for Cookies and Cream, and 5 for Rocky Road.\n";
      $input = fgets(STDIN);
      settype($input, "integer");
      while($input > 5 || $input < 1){
        echo "Incorrect input. What flavor would you like for scoop $displayX? 1 for Vanilla, 2 for Chocolate, 3 for Strawberry, 4 for Cookies and Cream, and 5 for Rocky Road.\n";
        $input = fgets(STDIN);
        settype($input, "integer");
      }
      if(is_numeric($input) && $input > 0 && $input < 6) {
        switch ($input){
          case 1:
            $customMilkshake->flavor = array_insert($customMilkshake->flavor, "Vanilla", $x);
            break;
          case 2:
            $customMilkshake->flavor = array_insert($customMilkshake->flavor, "Chocolate", $x);
            break;
          case 3:
            $customMilkshake->flavor = array_insert($customMilkshake->flavor, "Strawberry", $x);
            break;
          case 4:
            $customMilkshake->flavor = array_insert($customMilkshake->flavor, "Cookies and Cream", $x);
            break;
          case 5:
            $customMilkshake->flavor = array_insert($customMilkshake->flavor, "Rocky Road", $x);
            break;
          default:
            echo "A goblin ate all the ice cream. Please call the police.\n";
        }
      }
    }
    //get milk type
    echo "What kind of milk would you like? Press 1 for skim, press 2 for whole, or press 3 for 2%\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    while($input > 3 || $input < 1){
      echo "Incorrect input. What kind of milk would you like? Press 1 for skim, press 2 for whole, or press 3 for 2%\n";
      $input = fgets(STDIN);
      settype($input, "integer");
    }
    if($input == 1)
      $customMilkshake->milk = "skim";
    elseif($input == 2)
      $customMilkshake->milk = "whole";
    else
      $customMilkshake->milk = "2%";

    $customMilkshake->vessel = "cup";
    //get price. 25c for a cup.
    $customMilkshake->cost += ($customMilkshake->scoop_count * 0.5) + 0.25;

    print_order($customMilkshake);
    $globalValuesIn->subtotal += $customMilkshake->cost;
  }

  function makeFloat(&$globalValuesIn){
    echo "Floats are great on days like this! Floats come in cups, which are $0.25\n";
    $customFloat = new float("custom float");
    echo "How many scoops? Each one is $0.50\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    if ($input > 10){
      echo "*judges externally*\n";
    }
    elseif ($input > 5){
      echo "*judges internally*\n";
    }
    $customFloat->scoop_count = $input;
    //get scoop flavors
    $x = 0;
    for($x = 0; $x < $customFloat->scoop_count; $x++){
      $displayX = $x+1;
      echo "What flavor would you like for scoop $displayX? 1 for Vanilla, 2 for Chocolate, 3 for Strawberry, 4 for Cookies and Cream, and 5 for Rocky Road.\n";
      $input = fgets(STDIN);
      settype($input, "integer");
      while($input > 5 || $input < 1){
        echo "Incorrect input. What flavor would you like for scoop $displayX. 1 for Vanilla, 2 for Chocolate, 3 for Strawberry, 4 for Cookies and Cream, and 5 for Rocky Road.\n";
        $input = fgets(STDIN);
        settype($input, "integer");
      }
      if(is_numeric($input) && $input > 0 && $input < 6) {
        switch ($input){
          case 1:
            $customFloat->flavor = array_insert($customFloat->flavor, "Vanilla", $x);
            break;
          case 2:
            $customFloat->flavor = array_insert($customFloat->flavor, "Chocolate", $x);
            break;
          case 3:
            $customFloat->flavor = array_insert($customFloat->flavor, "Strawberry", $x);
            break;
          case 4:
            $customFloat->flavor = array_insert($customFloat->flavor, "Cookies and Cream", $x);
            break;
          case 5:
            $customFloat->flavor = array_insert($customFloat->flavor, "Rocky Road", $x);
            break;
          default:
            echo "Something weird happened.\n";
        }
      }
    }
    //get soda type
    echo "What kind of soda would you like? Press 1 for root beer, press 2 for Coke, or press 3 for Cheerwine\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    while($input > 3 || $input < 1){
      echo "What kind of soda would you like? Press 1 for root beer, press 2 for Coke, or press 3 for Cheerwine\n";
      $input = fgets(STDIN);
      settype($input, "integer");
    }
    if($input == 1)
      $customFloat->soda= "root beer";
    elseif($input == 2)
      $customFloat->soda = "Coke";
    else
      $customFloat->soda = "Cheerwine";

    $customFloat->vessel = "cup";
    //get price. 25c for a cup.
    $customFloat->cost += ($customFloat->scoop_count * 0.5) + 0.25;

    print_order($customFloat);
    $globalValuesIn->subtotal += $customFloat->cost;
  }

  function makeCustomOrder(&$globalValuesIn){
    echo "What kind of dessert do you want?\n";
    echo "1 for ice cream, 2 for milkshake, 3 for float\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    if(is_numeric($input)) {
      if($input == 1)
        makeIceCream($globalValuesIn);
      if($input == 2)
        makeMilkshake($globalValuesIn);
      if($input == 3)
        makeFloat($globalValuesIn);
    }
  }

  function cashier(&$globalValuesIn) {
    echo "Select your Special number or press 4 to make your own. Press enter to submit.\n";
    $input = fgets(STDIN);
    settype($input, "integer");
    while($input > 5 || $input < 1){
      echo "Incorrect input. Please select Specials #1-3 by entering the respective number ('1', '2', or '3') or make your own by pressing '4'. Press enter to submit.\n";
      $input = fgets(STDIN);
      settype($input, "integer");
    }
    echo "Good choice!\n";
    if($input != 4){
      $order = new dessert("Special $input");
      $order = makeSpecial($input);
      print_order($order);
      $globalValuesIn->subtotal += $order->cost;
    }
    else
      makeCustomOrder($globalValuesIn);
  }

  ?>
