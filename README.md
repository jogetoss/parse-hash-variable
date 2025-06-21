# Description

1. #envVariableParse.**key**# or #appVariableParse.**key**#

The intent of this hash variable is for app designer to factorize codes that would otherwise be repeated in multiple environment variables' values, making code upkeep difficult, into a single environment variable.

The #envVariableParse.**key**# is similar to #beanshell.**key**# but it does not execute the script nor accept parameter arguments, but it will parse any hash variables found in there. Compared to the environment variable hash variable, the environment variable hash variable does **not** parse hash variables.

Sample usage:
![SCR-20250503-hmmj](https://github.com/user-attachments/assets/02efe3aa-46ee-4c5d-baf5-b35487ec4e79)

2. #formParse.TABLE.ID#

This hash is similar to #form.TABLE.ID# except that its returned content will be parsed as hash variable. If there's any character "#" found, it will be preserved as well. The intent of preserving the # character is for further (hash variable) parsing of its content. 

Sample use case: Populating email tool's subject from a fixed row in form data.

![SCR-20250621-soam](https://github.com/user-attachments/assets/7b0bbbf2-d54f-4518-9c58-86bb42dc7a3b)

# Getting Help

JogetOSS is a community-led team for open source software related to the [Joget](https://www.joget.org) no-code/low-code application platform.
Projects under JogetOSS are community-driven and community-supported.
To obtain support, ask questions, get answers and help others, please participate in the [Community Q&A](https://answers.joget.org/).

# Contributing

This project welcomes contributions and suggestions, please open an issue or create a pull request.

Please note that all interactions fall under our [Code of Conduct](https://github.com/jogetoss/repo-template/blob/main/CODE_OF_CONDUCT.md).

# Licensing

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

NOTE: This software may depend on other packages that may be licensed under different open source licenses.
