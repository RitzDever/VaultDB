#**Vault - Abstraction Library for Bukkit**

###*For Developers:*

Please see the VaultAPI page for information on developing with Vault's API. In the past you would use the same artifact as servers installed, but the API has now been split from the main project and is under a different artifact name. Please make sure you accomodate for this change in your build process.

YES! This means that you can use vaults API to get info from the db!
It will lagg a bit in this current build, but I am currently implementing multi threading to make it faster!

###*Installing*

#####Config:

An example:
```
database:
  address: 127.0.0.1:3306
  username: root
  password:
  table: currency
  name: test
format: ${}
initial-balance: 50
prune-age: 30d

```

I have a database on my localhost(port 3306 is the default port), the credentials is user(root) and password().
The database is "test" and the table is, "currency."
Adding three columns, "balance", "account", and "last_update", the datatypes are "varchar" and there lengths are 255.

Currently working on automaking the tables.


###*License*

Copyright (C) 2014 RitzDever

VaultDB is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

VaultDB is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public License along with VaultDB. If not, see http://www.gnu.org/licenses/.

###*Building*

VaultDB is maven! Read the pom.xl

###*Dependencies*

Vault, duh!

###*Supported Plugins*

Since this depends on vault, you can have these economy plugins:

3co (http://forums.bukkit.org/threads/22461/)
AEco
BOSEconomy 6 (http://forums.bukkit.org/threads/19025/)
BOSEconomy 7
CommandsEX Economy (http://dev.bukkit.org/server-mods/commandsex/)
CraftConomy2 (http://dev.bukkit.org/server-mods/craftconomy/)
CraftConomy3 (http://dev.bukkit.org/server-mods/craftconomy/)
CurrencyCore (http://dev.bukkit.org/server-mods/currency/)
Dosh
EconXP (http://dev.bukkit.org/server-mods/econxp/)
Essentials Economy (http://forums.bukkit.org/threads/15312/)
eWallet (http://dev.bukkit.org/server-mods/ewallet/)
GoldIsMoney
GoldIsMoney2
Gringotts
iConomy 4 (http://forums.bukkit.org/threads/40/)
iConomy 5 (http://forums.bukkit.org/threads/40/)
iConomy 6 (http://forums.bukkit.org/threads/40/)
McMoney
Miconomy
MineConomy (http://dev.bukkit.org/server-mods/mineconomy/)
MineFaconomy2
MultiCurrency
SDFEconomy
TAEcon
XPBank
Permissions

bPermissions
bPermissions 2 (http://dev.bukkit.org/server-mods/bpermissions/)
DroxPerms
Group Manager (Essentials) (http://forums.bukkit.org/threads/15312/)
Permissions 3 (http://forums.bukkit.org/threads/18430/)
PermissionsBukkit
Permissions Ex (http://forums.bukkit.org/threads/18140/)
Privileges
rscPermissions
SimplyPerms
SuperPerms (Bukkit's default)
TotalPermissions (http://dev.bukkit.org/bukkit-mods/totalpermissions)
XPerms
zPermissions
Chat

bPermissions
Group Manager (Essentials)
iChat
mChat
mChatSuite
Permissions3
PEX
rscPermissions
TotalPermissions
zPermissions
