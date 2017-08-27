# Pre-work - *Simple Todo List*

**Name of your app** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Harper Xue

Time spent: 5 hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [x] Add support for completion due dates for todo items (and display within listview item)
* [x] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [x] Add support for selecting the priority of each todo item (and display in listview item)
* [x] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [x] List anything else that you can get done to improve the app functionality!
Strike through the todos which are done.

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://imgur.com/1FwyQE1.gif' title='Simple Todo List Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** Android sdk empowers developers with tools to fully integrate with Google's ecosystem which is really friendly. 

Web development and android, both are related to design development and core front-end/back-end programming. For web development, using html/css/js to render layout and user interfaces, while for android development, more java and oop design programming. Considering the UI part, HTML is a standard designed to display web pages. For android, the xml tags are defined in android according to the UI framework classes and the attributes are defined corresponding to the member variables of the class in the UI framework. The android XML layout files are custom configuration files that are intended to be parsed. XML enables us to create custom tags. Consider we created a custom view, we can reference them from xml itself. So the idea of UI separation from logics, stays intact.  

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:  Adapter acts as a bridge between an AdapterView and the underlying data for that view. The Adapter provides access to the data items. The Adapter is also responsible for making a View for each item in the data set. If no adapter, data can not be displayed and rendered with the layout correctly. Adapter enables you to reuse some view with new data.

ConvertView is used to reuse old view. ConvertView is the ListView Item Cache that is not visible, and hence it can be reused. It lets the ListView need not create a lot of ListItems, hence saving memeory and making the ListView more smooth.



## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [yyyy] [name of copyright owner]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
