Rhyme City is a demo Android app for finding rhyming words. It uses the [Words API](https://www.wordsapi.com/).

### MVP and more

I created this app to demonstrate how I use the model-view-presenter UI pattern in practice, along with a few of my favorite libraries.

I use Dagger's scoped graphs to inject the Presenter into the Fragment (the "V" in MVP). The Fragment implements a view interface, and its methods are called by the Fragment's Presenter. Read more about that [here](http://mattlogan.me/decoupling-the-presenter).

### Testing

Unit tests use Mockito and JUnit.

### Api key

You'll need an [api key](https://www.mashape.com/wordsapi/wordsapi) for the Words API to run it yourself. Add it to a strings resources file and call it `api_key`.

### License

```
The MIT License (MIT)

Copyright (c) 2015 Matthew Logan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
