import scholar
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
import re
cred = credentials.Certificate("Cert")
firebase_admin.initialize_app(cred, {'databaseURL': 'DatabaseURL'})
ref = db.reference('/professor/')

def cleanhtml(raw_html):
  cleanr = re.compile('<.*?>')
  cleantext = re.sub(cleanr, '', raw_html)
  return cleantext


search_query = scholar.search_author('Ganesh Neelakanta Iyer')
for i in search_query:
    i=i.fill()
    print(i)
    l=[]
    for pub in i.publications:
        try:
            k = pub.fill()
            c = {
                'abstract': cleanhtml(str(k.bib['abstract'])),
                'title': k.bib['title'],
                'url': k.bib['url'],
                'year': k.bib['year'],
                'author': k.bib['author'],
                'citedby': k.citedby,
                'publisher': k.bib['publisher']
            }
            print(c)
            l.append(c)
        except Exception:
            continue

    con = {
        'name': i.name,
        'publications': l,
        'cites_per_year': i.cites_per_year,
        'citedby': i.citedby,
        'hindex': i.hindex,
        'hindex5y': i.hindex5y,
        'i10index': i.i10index,
        'i10index5y':i.hindex5y,
        'interests':i.interests,
        'url_picture': i.url_picture
        }
    print(con)
    ref.child(i.name).set(con)
