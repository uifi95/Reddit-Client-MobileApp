
'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TextInput,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View,
  BackAndroid
} from 'react-native';

class EditArticle extends Component {

  constructor(props) {
    super(props);
    console.log(props);
    this.state = {
        articleTitle: props.article.title,
        articleContent: props.article.content,
    }
  }

  navigate(routeName) {
      this.props.navigator.push({
        name: routeName
      });
    }

  render() {
    return (
      <View style={styles.container}>
        <Text style={{color:"#E0E0E0"}}>Title</Text>
        <TextInput style={{width: 150,height: 40, borderColor: '#424242', color:"#E0E0E0", borderWidth: 1}}
          onChangeText={(text) => this.setState({articleTitle: text})}
          value={this.state.articleTitle}
        />
        <Text style={{color:"#E0E0E0"}}>Content</Text>
        <TextInput style={{width: 150,height: 40, borderColor: '#424242', color:"#E0E0E0", borderWidth: 1}}
          onChangeText={(text) => this.setState({articleContent: text})}
          value={this.state.articleContent}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'flex-start',
    alignItems: 'center',
    backgroundColor: '#424242',
    padding: 10,
    paddingTop: 80
  },
  input: {
    height: 50,
    marginTop: 10,
    padding: 4,
    fontSize: 18,
    borderWidth: 1,
    borderColor: '#48bbec'
  },
  button: {
    height: 50,
    backgroundColor: '#48BBEC',
    alignSelf: 'stretch',
    marginTop: 10,
    justifyContent: 'center'
  },
  buttonText: {
    fontSize: 22,
    color: '#FFF',
    alignSelf: 'center'
  },
  heading: {
    fontSize: 30,
  },
  error: {
    color: 'red',
    paddingTop: 10
  },
  success: {
    color: 'green',
    paddingTop: 10
  },
  loader: {
    marginTop: 20
  }
});

export default EditArticle
