//
//  HomeViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit
import CoreLocation

class HomeViewController: UIViewController, CLLocationManagerDelegate {

  @IBOutlet weak var viewDropDown: UIView!
  
  let menuItems = ["REVISTAS", "LIBROS", "CÓMICS", "PERIÓDICOS"]
  var menuView: BTNavigationDropdownMenu!
  
  var locationManager: CLLocationManager!

  override func viewDidLoad() {
    super.viewDidLoad()
    
    self.initLocation()
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
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
}
