//
//  HomeViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit
import CoreLocation
import MapKit

class HomeViewController: UIViewController, CLLocationManagerDelegate, MKMapViewDelegate {

  @IBOutlet weak var mapView: MKMapView!
  @IBOutlet weak var viewDropDown: UIView!
  
  let menuItems = ["REVISTAS", "LIBROS", "CÓMICS", "PERIÓDICOS"]
  var menuView: BTNavigationDropdownMenu!
  
  let regionRadius: CLLocationDistance = 800
  var locationManager: CLLocationManager!

  override func viewDidLoad() {
    super.viewDidLoad()
    
    self.initLocation()
    
    self.mapView.showsUserLocation = true
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  override func viewWillAppear(animated: Bool) {
    self.title = "Emergencia"
  }
  
  func initLocation() {
    locationManager = CLLocationManager()
    locationManager.delegate = self
    locationManager.desiredAccuracy = kCLLocationAccuracyBest
    locationManager.requestAlwaysAuthorization()
    //    locationManager.startMonitoringSignificantLocationChanges()
    locationManager.startUpdatingLocation()
  }
  
  private func setupMenuSection() {
    menuView = BTNavigationDropdownMenu(title: menuItems[0], items: menuItems)
    menuView.cellTextLabelFont = UIFont(name: "GothamNarrow-Bold", size: 15)
    //    menuView.didSelectItemAtIndexHandler!(indexPath: publicationType! - 1)
    
    menuView.didSelectItemAtIndexHandler = {(indexPath: Int) -> () in
      //      print("Did select item at index: \(indexPath)")
      //      self.menuView.setMenuTitle(self.menuItems[indexPath])
    }
    
    self.navigationItem.titleView = menuView
  }
  
  func mapView(mapView: MKMapView, didUpdateUserLocation userLocation: MKUserLocation) {
    let coordinateRegion = MKCoordinateRegionMakeWithDistance(userLocation.coordinate,
                                                              regionRadius, regionRadius)
    
    mapView.setRegion(self.mapView.regionThatFits(coordinateRegion), animated: true)
  }
  
  func mapView(mapView: MKMapView, viewForAnnotation annotation: MKAnnotation) -> MKAnnotationView? {
    let identifier = "User"
    
    var annotationView = mapView.dequeueReusableAnnotationViewWithIdentifier(identifier)
    
    if annotation.isKindOfClass(MKUserLocation.self) {
      annotationView = MKPinAnnotationView(annotation: annotation, reuseIdentifier: identifier)
      annotationView!.canShowCallout = true
      annotationView!.image = UIImage(named: "ImgLocation")
    } else {
      if annotationView == nil{
        annotationView = MKPinAnnotationView(annotation: annotation, reuseIdentifier: identifier)
        annotationView!.canShowCallout = true
      } else {
        annotationView!.annotation = annotation
      }
    }
    return annotationView
  }
  
  func locationManager(manager: CLLocationManager, didUpdateToLocation newLocation: CLLocation, fromLocation oldLocation: CLLocation) {
    //    print(newLocation.coordinate.latitude)
    //    print(newLocation.coordinate.longitude)
    
    if CLLocationManager.locationServicesEnabled(){
      self.saveCurrentLocation(newLocation.coordinate.latitude, lon: newLocation.coordinate.longitude)
      
//      let pointAnnotation = MKPointAnnotation()
//      pointAnnotation.coordinate.latitude = newLocation.coordinate.latitude
//      pointAnnotation.coordinate.longitude = newLocation.coordinate.longitude
//      pointAnnotation.title = ""
//      
//      self.mapView.addAnnotation(pointAnnotation)
      
    }
  }
  
  func locationManager(manager: CLLocationManager, didChangeAuthorizationStatus status: CLAuthorizationStatus) {
    var lat = 19.270781
    var lon = -99.269498
    
    if status == .AuthorizedAlways {
      if CLLocationManager.isMonitoringAvailableForClass(CLCircularRegion.self) {
        if CLLocationManager.isRangingAvailable() {
          //          print(manager.location?.coordinate.latitude)
          //          print(manager.location?.coordinate.longitude)
          
          lat = (manager.location?.coordinate.latitude)!
          lon = (manager.location?.coordinate.longitude)!
        }
      }
    }
    
    self.saveCurrentLocation(lat, lon: lon)
  }
  
  func saveCurrentLocation(lat: Double, lon: Double) {
    NSUserDefaults.standardUserDefaults().setObject(lat, forKey: "lat")
    NSUserDefaults.standardUserDefaults().setObject(lon, forKey: "lon")
    NSUserDefaults.standardUserDefaults().synchronize()
  }
}
