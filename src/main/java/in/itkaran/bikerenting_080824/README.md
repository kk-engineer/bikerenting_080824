# Bike Renting Service

## Problem Statement
* You've been hired by a bike shop to create software 
to help them with their rental operation.
* This includes automating activities such as keeping track of 
inventory, customers, stock items that are rented, customer fee accrual etc...
* The store rents two types of products: Bikes and Scooters.
* Each bike is made in one of three different sizes, Small, Medium, Large 
and will fit small, medium and large humans respectively. 
* We'd like to be sure our customers get a bike that fits them and will need to track size of our bikes.
* The scooters are available in electric motor or gas motor styles.
* The styles of scooters are very different, 
so we want to track them separately, 
so we can match our customers needs to the appropriate scooter.



## Required Queries
* How many small bikes do you have?
* What vehicle are there for rent?
* Does this customer have a balance? (aka owe us money)
* What vehicle are rented?
* Are there vehicles that are overdue for return? Who has them?
* What vehicles has a customer rented?


## Required commands
* Add a vehicle to inventory
* Add a customer
* Remove a vehicle from inventory (permanently not for rental, i.e. it's been damaged)
* Record that a vehicle is rented to a customer
* Create a charge for the customer



## Deliverables
Can include but not limited to:
* API spec
* Class diagrams
* Schema design


## API Specifications
* /vehicles
  * GET - Get all vehicles
  * POST - Add a vehicle to inventory

* /vehicles/{reg}
  * PATCH - Update a vehicle
  * DELETE - Remove(soft delete) a vehicle from inventory

* /vehicles/status/{status}
  * GET - Get all vehicles by status

* /vehicles/available
  * GET - Get all available vehicles

* /vehicles/type/{type}
  * GET - Get all vehicles by type

* /customers
  * GET - Get all customers
  * POST - Add a customer
  
* /rentals
  * GET - Get all rentals
  * POST - Record that a vehicle is rented to a customer
  
* /rentals/bill/{pnr}
    * GET - Get bill for a customer by PNR