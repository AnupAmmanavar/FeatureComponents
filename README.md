# FeatureComponents
Creating loosely-coupled Reactive Feature Components


## Overview
* Re-usability is a pattern that solves the problem of duplication. It can be as simple as creating Functions, Classes, Interfaces to more complex **UI-Components** and **Feature-Components**.

* The article focuses initially on the re-usability of UI-Views(**UIComponents**) and then the **FeatureComponents**. 
* Emphasis is on the [Dependency-Inversion Principle](https://www.oodesign.com/dependency-inversion-principle.html), where both the Low-level(Feature Component) and High-level component (One incorporating the low-level components) both depend on abstraction, to create loosely-coupled systems.



## A peek into UI Component [UI-level]

Before proceeding to Feature Components, we’ll start with a brief overview of UI-Components(re-usability of Views).

![UIComponent](https://miro.medium.com/max/1400/1*cwz_Vtu928DlPWlqOVwrLg.png)

* Data is sent downwards from **HLC → LLC**, while the Events are sent from the **LLC → HLC**.

**Low-Level Component (UIComponent)**: 
It only renders itself based on the data provided by the High-level component. And propagates the events to the High-level component(👇). It’s devoid of business-logic.

```kotlin
class UIComponent(
  private val data: ComponentData,
  private val eventDispatch: UIComponentEventDispatch) {
  
  // Renders based on the data provided by HLC
  fun render(view: UIComponentView) {
    view.data = data
    view.invalidate()
  }
  
  fun onRandomEvent(payLoad: Data) {
    // Sends the event to the HLC
    eventDispatch.handleEvent1(payLoad)
  }
}

interface UIComponentEventDispatch {
  fun handleEvent1(payLoad: Data)
}
```
**High-level Component**: Provides the data for the UI-Components(LLC), and handles the events from the UIComponent(👇). Example: Click events. It holds all the business logic. It is a single source of truth that holds the state/data for all the UI-Component.

```kotlin
class HighLevelComponent {
  // Provides the data for UI component and passes the handler for handling events from UIComponent dispatch
  val uiComponent = UIComponent(data, UIComponentEventHandler())
  
  // Handles the events from the UIComponent.
  inner class UIComponentEventHandler: UIComponentEventDispatch {
    fun handleEvent1(payLoad: Data) { ... }
  }
  
  fun renderPage() {
    uiComponent.render(uiComponentView)
  }
}

```

