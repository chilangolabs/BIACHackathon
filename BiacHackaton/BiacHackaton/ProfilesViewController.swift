//
//  ProfilesViewController.swift
//  BiacHackaton
//
//  Created by Rodrigo Lara Ruano on 21/06/16.
//  Copyright © 2016 OECD. All rights reserved.
//

import UIKit

class ProfilesViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
  
  @IBOutlet weak var profilesTableView: UITableView!
  
  var profilesArray: NSMutableArray = []

  override func viewDidLoad() {
    super.viewDidLoad()
    
    // Do any additional setup after loading the view.
    self.profilesTableView.registerNib(UINib(nibName: "ProfileTableViewCell", bundle: nil), forCellReuseIdentifier: "ProfileCell")
  }
  
  override func didReceiveMemoryWarning() {
    super.didReceiveMemoryWarning()
    // Dispose of any resources that can be recreated.
  }
  
  override func viewWillAppear(animated: Bool) {
    self.title = "Perfiles"
    
    self.loadProfiles()
  }
  
  func loadProfiles() {
    Server.getMedicalCard { (response) in
      if response.type == .OK {
        if let responseData = response.responseObject as? NSDictionary {
//          print(responseData)
          let json = JSON(responseData)
          //If json is .Dictionary
          for (_, subJson):(String, JSON) in json {
//              print(subJson)
            for (_, dataJson):(String, JSON) in subJson {
//              print(dataJson)
              self.profilesArray.addObject(dataJson.dictionaryObject!)
              self.profilesTableView.reloadData()
            }
            
          }
        }
      }
    }
  }
  
  func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
    return self.profilesArray.count
  }
  
  func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
    let cell = tableView.dequeueReusableCellWithIdentifier("ProfileCell", forIndexPath: indexPath)
    
    if let profileCell = cell as? ProfileTableViewCell {
      if let data = self.profilesArray[indexPath.row] as? NSDictionary {
        profileCell.name.text = (indexPath.row == 0) ? "Mamá" : "Papá"
        profileCell.type.text = data.objectForKey("relation") as? String
      }
    }
    
    return cell
  }
  
  func tableView(tableView: UITableView, heightForRowAtIndexPath indexPath: NSIndexPath) -> CGFloat {
    return 60
  }
  
  func tableView(tableView: UITableView, didSelectRowAtIndexPath indexPath: NSIndexPath) {
    
  }
}
