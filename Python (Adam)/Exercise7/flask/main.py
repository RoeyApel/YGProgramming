import flask

app = flask.Flask("ITsafe API Server", static_url_path='')

@app.route('/')
def hello_world():
    return "Hello World"

debug = True
app.run(host='127.0.0.1', port=1337, debug=debug)
