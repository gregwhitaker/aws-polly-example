# aws-polly-example
[![Build Status](https://travis-ci.org/gregwhitaker/aws-polly-example.svg?branch=master)](https://travis-ci.org/gregwhitaker/aws-polly-example)

Example of using [Amazon Polly](https://aws.amazon.com/polly/) for text-to-speech integration.

## Running the Example
The example can be run using the following Gradle command:

    $ ./gradlew run

Once the application has started, point your web browser to [http://localhost:5050](http://localhost:5050) to access the test page.

**NOTE:** The example is using the `DefaultAWSCredentialsProvider` so you can supply your AWS credentials using the `.aws/credentials` file, as system properties, or as environment variables.

## Bugs and Feedback

For bugs, questions and discussions please use the [Github Issues](https://github.com/gregwhitaker/aws-polly-example/issues).

## License
Copyright 2017 Greg Whitaker

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
