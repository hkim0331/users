# USERS

clojure/reitit/next.jdbc で CRUD の練習。

## Usage

```shell
$ lein run
$ http :3000/users
$ http :3000/users sid='123' name='my name' login='acc' password='as you like'
$ http :3000/users/acc
$ http put :3000/users login=acc password='new password'
$ http delete :3000/users login=acc
```

## License

Copyright © 2021 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
