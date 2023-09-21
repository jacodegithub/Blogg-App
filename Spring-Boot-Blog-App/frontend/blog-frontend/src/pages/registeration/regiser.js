import React, { useState } from 'react'
import { Base } from '../../component/base'
import { Button, Card, CardBody, CardHeader, Col, Container, Form, FormGroup, Input, Label, Row } from 'reactstrap'
import { SignUp } from '../../service/loginLogoutService'


export const Register = () => {

  const [user, setUser] = useState({
    username: '',
    emailId: '',
    password: ''
  })

  const [error, setError] = useState({
    errors: {},
    isError: false
  })

  const handleChange = (e, field) => {
    setUser({ ...user, [field]: e.target.value })
  }

  const submitForm = (event) => {
    event.preventDefault()

    SignUp(user).then(data =>{
      setUser({
        username: '',
        emailId: '',
        password: ''
      })
    }).catch(error => {
      console.log('login error', error)
      setError({
        errors: error,
        isError: true
      })
    })
  }

  return (
    <Base>
      <Container>
        <Row className='d-flex my-5'>
          <Col sm={{size: 4, offset: 3}}>
            <Card>
              <CardHeader>
                Sign Up
              </CardHeader>
              <CardBody>
                <Form onSubmit={submitForm}>
                  <FormGroup>
                    <Label htmlFor='username'>Username</Label>
                    <Input 
                      type='text'
                      placeholder='username'
                      id='username'
                      value={user.username}
                      onChange={(e) => handleChange(e, 'username')}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label htmlFor='email'>Email</Label>
                    <Input 
                      type='text'
                      placeholder='email'
                      id='email'
                      value={user.emailId}
                      onChange={(e) => handleChange(e, 'emailId')}
                    />
                  </FormGroup>

                  <FormGroup>
                    <Label htmlFor='password'>Password</Label>
                    <Input 
                      type='text'
                      placeholder='password'
                      id='password'
                      value={user.password}
                      onChange={(e) => handleChange(e, 'password')}
                    />
                  </FormGroup>

                  <Button>Sign Up</Button>
                </Form>
              </CardBody>
            </Card>
          </Col>
        </Row>
      </Container>
    </Base>
  )
}
