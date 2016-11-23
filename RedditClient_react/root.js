'use strict';
import React, { Component } from 'react';
import {
  StyleSheet,
  TouchableHighlight,
  AsyncStorage,
  Text,
  View,
  Linking,
  TextInput,
  BackAndroid
} from 'react-native';

import Button from 'react-native-button';

class Root extends Component {

  constructor() {
      super();
      this.state = {
          text: ''
      }
    }

    componentDidMount(){
        BackAndroid.addEventListener('hardwareBackPress', () => {
            if (this.props.navigator && this.props.navigator.getCurrentRoutes().length > 1) {
                this.props.navigator.pop();
                return true;
            }
            return false;
        });
      }

    handleButtonPress() {
      Linking.openURL('mailto:myemail@gmail.com?subject=MySubject&body=' + this.state.text);
    }

  navigate(routeName) {
    this.props.navigator.push({
      name: routeName
    });
  }

  render() {
	console.log("here3");
    return (
        <View style={styles.container}>
        <TextInput
          style={{width: 180,height: 40}}
          onChangeText={(text) => this.setState({text})}
          value={this.state.text}
        />
        <Button style={{color:"white"}}
            onPress={() => this.handleButtonPress()}>
            Send
        </Button>

        <Button onPress={ this.navigate.bind(this, 'articlelist') } style={styles.button}>
          <Text style={styles.text}>Article List</Text>
        </Button>
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
    paddingTop: 180
  },
  text: {
    color: "white",
    backgroundColor: "#757575",
    fontSize: 20,
    padding: 10,
    marginTop: 30,
    borderRadius: 5,
  },
  button: {
  }
});

export default Root
