#!/bin/sh
http :3000/users sid='123' name='my name' login='user1' password='pass1'
http :3000/users sid='12345' name='user2' login='user2' password='pass2'
http :3000/users sid='12356' name='user3' login='user3' password='pass3'
http :3000/users sid='12357' name='user4' login='user4' password='pass4'
http :3000/users sid='12368' name='user5' login='user5' password='pass5'